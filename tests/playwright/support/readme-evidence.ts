import fs from 'node:fs';
import path from 'node:path';
import { test, type Page, type TestInfo } from 'playwright/test';

type StepStatus = 'passed' | 'failed';

interface EvidenceStep {
  title: string;
  status: StepStatus;
  screenshot: string;
  error?: string;
}

interface ReadmeEvidenceOptions {
  command: string;
  readmePath: string;
  runLabel?: string;
}

export class ReadmeEvidenceRecorder {
  private readonly readmePath: string;
  private readonly evidenceDir: string;
  private readonly startedAt = new Date();
  private readonly steps: EvidenceStep[] = [];
  private createdObjectUrl = '';
  private runtimeObjectName = '';

  constructor(
    private readonly testInfo: TestInfo,
    options: ReadmeEvidenceOptions,
  ) {
    this.readmePath = path.resolve(options.readmePath);
    this.evidenceDir = path.join(path.dirname(this.readmePath), 'evidence', safeSlug(options.runLabel || this.testInfo.title));
    fs.mkdirSync(this.evidenceDir, { recursive: true });
    this.writeReadme('running', options.command);
  }

  setCreatedObjectUrl(url: string): void {
    this.createdObjectUrl = url;
    this.writeReadme('running');
  }

  setRuntimeObjectName(name: string): void {
    this.runtimeObjectName = name;
    this.writeReadme('running');
  }

  async step(title: string, page: Page | (() => Page), body: () => Promise<void>): Promise<void> {
    await test.step(title, async () => {
      try {
        await body();
        await this.captureStep(title, getPage(page), 'passed');
      } catch (error) {
        await this.captureStep(title, getPage(page), 'failed', error);
        throw error;
      }
    });
  }

  finalize(status: StepStatus = 'passed'): void {
    this.writeReadme(status);
  }

  private async captureStep(title: string, page: Page, status: StepStatus, error?: unknown): Promise<void> {
    const screenshotPath = path.join(this.evidenceDir, `${String(this.steps.length + 1).padStart(2, '0')}-${safeSlug(title)}.png`);
    await page.screenshot({ path: screenshotPath, fullPage: true }).catch(() => undefined);
    await this.testInfo.attach(`${String(this.steps.length + 1).padStart(2, '0')} ${title}`, {
      path: screenshotPath,
      contentType: 'image/png',
    }).catch(() => undefined);

    this.steps.push({
      title,
      status,
      screenshot: path.relative(path.dirname(this.readmePath), screenshotPath),
      error: error ? oneLineError(error) : undefined,
    });
    this.writeReadme(status === 'failed' ? 'failed' : 'running');
  }

  private writeReadme(status: 'running' | StepStatus, command = ''): void {
    const existing = fs.existsSync(this.readmePath) ? fs.readFileSync(this.readmePath, 'utf8') : `# ${this.testInfo.title}\n`;
    const marker = '## Latest Execution Evidence';
    const beforeMarkerRaw = existing.includes(marker) ? existing.slice(0, existing.indexOf(marker)).trimEnd() : existing.trimEnd();
    const beforeMarker = this.steps.length ? this.withTestStepScreenshots(beforeMarkerRaw) : beforeMarkerRaw;
    const lines = [
      beforeMarker,
      '',
      marker,
      '',
      `- Status: ${status}`,
      `- Started: \`${this.startedAt.toISOString()}\``,
      command ? `- Command: \`${command}\`` : undefined,
      this.createdObjectUrl ? `- Created object URL: \`${this.createdObjectUrl}\`` : undefined,
      this.runtimeObjectName ? `- Runtime object: \`${this.runtimeObjectName}\`` : undefined,
      `- Playwright JSON result: \`../../test-results/playwright/results.json\``,
      '',
      '### Step Screenshots',
      '',
      '| # | Step | Status | Screenshot |',
      '| ---: | --- | --- | --- |',
      ...this.steps.map((step, index) => {
        const screenshotLink = step.screenshot ? `[${path.basename(step.screenshot)}](${step.screenshot})` : '';
        return `| ${index + 1} | ${escapeTable(step.title)} | ${step.status} | ${screenshotLink} |`;
      }),
    ].filter((line): line is string => line !== undefined);

    if (this.steps.some((step) => step.error)) {
      lines.push('', '### Step Errors', '');
      for (const step of this.steps.filter((item) => item.error)) {
        lines.push(`- ${step.title}: ${step.error}`);
      }
    }

    fs.writeFileSync(this.readmePath, `${lines.join('\n')}\n`);
  }

  private withTestStepScreenshots(markdown: string): string {
    const testStepsMarker = '## Test Steps';
    const nextHeadingPattern = /\n## /g;
    const start = markdown.indexOf(testStepsMarker);
    if (start === -1) return markdown;

    nextHeadingPattern.lastIndex = start + testStepsMarker.length;
    const nextHeading = nextHeadingPattern.exec(markdown);
    const end = nextHeading ? nextHeading.index : markdown.length;
    const before = markdown.slice(0, start);
    const section = markdown.slice(start, end);
    const after = markdown.slice(end);
    const cleanSection = stripStepEvidence(section);
    const lines = cleanSection.split('\n');

    let stepIndex = 0;
    const updated: string[] = [];

    for (const line of lines) {
      updated.push(line);
      if (/^\d+\.\s+/.test(line)) {
        const evidence = this.steps[stepIndex];
        if (evidence) {
          updated.push(`   - Status: ${evidence.status}`);
          updated.push(`   - Screenshot: [${path.basename(evidence.screenshot)}](${evidence.screenshot})`);
          if (evidence.error) updated.push(`   - Error: ${evidence.error}`);
        }
        stepIndex += 1;
      }
    }
    return `${before}${updated.join('\n')}${after}`;
  }
}

function getPage(page: Page | (() => Page)): Page {
  return typeof page === 'function' ? page() : page;
}

function safeSlug(value: string): string {
  return value
    .toLowerCase()
    .replace(/[^a-z0-9]+/g, '-')
    .replace(/^-+|-+$/g, '')
    .slice(0, 90) || 'step';
}

function escapeTable(value: string): string {
  return value.replace(/\|/g, '\\|').replace(/\r?\n/g, ' ');
}

function stripStepEvidence(markdown: string): string {
  return markdown
    .split('\n')
    .filter((line) => !/^\s+- (Status|Screenshot|Error): /.test(line))
    .join('\n');
}

function oneLineError(error: unknown): string {
  const message = error instanceof Error ? error.message : String(error);
  return message
    .replace(/\u001b\[[0-9;]*m/g, '')
    .replace(/\s+/g, ' ')
    .trim();
}
