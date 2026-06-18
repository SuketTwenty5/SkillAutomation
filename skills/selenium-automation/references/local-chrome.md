# Local Chrome Setup

Use a dedicated Chrome profile for AI-driven test runs. Do not attach to the consultant's daily browsing profile.

## macOS

```bash
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome \
  --remote-debugging-port=9222 \
  --user-data-dir="$HOME/.selenium-ai-chrome"
```

## Windows PowerShell

```powershell
& "C:\Program Files\Google\Chrome\Application\chrome.exe" `
  --remote-debugging-port=9222 `
  --user-data-dir="$env:USERPROFILE\.selenium-ai-chrome"
```

## Linux

```bash
google-chrome \
  --remote-debugging-port=9222 \
  --user-data-dir="$HOME/.selenium-ai-chrome"
```

## Selenium Attachment

```java
ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
WebDriver driver = new ChromeDriver(options);
```

## Selenide Attachment

Use a custom `WebDriverProvider` and set:

```java
Configuration.browser = LocalChromeProvider.class.getName();
```

The `assets/runner-template` folder contains a working example.
