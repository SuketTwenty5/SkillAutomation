// Shared XPath string helpers for the Page Object Model.
// Single home for these so specs and page objects never re-declare them (drift guard).

/** Build a safe XPath string literal, handling embedded quotes via concat(). */
export function xpathLiteral(value: string): string {
  if (!value.includes("'")) return `'${value}'`;
  if (!value.includes('"')) return `"${value}"`;
  return `concat(${value
    .split("'")
    .map((part) => `'${part}'`)
    .join(', "\'", ')})`;
}

/** Case-insensitive contains() predicate against normalize-space(.). */
export function ciContainsText(value: string): string {
  return `contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), ${xpathLiteral(
    value.toLowerCase(),
  )})`;
}

/** Collapse NBSP + whitespace and trim — for comparing UI values. */
export function normalizeText(value: string): string {
  return value.replace(/\u00a0/g, " ").replace(/\s+/g, " ").trim();
}

/** Escape a string for safe use inside a RegExp. */
export function escapeRegex(value: string): string {
  return value.replace(/[.*+?^${}()|[\]\\]/g, '\\$&');
}
