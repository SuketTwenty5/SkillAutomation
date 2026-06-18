# Action Catalog

Use this file as the bridge between manual test language and automation code. Keep entries short and concrete.

## Template

```markdown
## Domain or Feature

### action_name
Manual phrases:
- phrase from Confluence/manual scripts
- another likely phrase

Code:
`ClassName.methodName(Type arg)`

Example:
```java
page.methodName(value);
```

Notes:
- required preconditions
- test data expectations
```

## Example Entries

## Authentication

### login_as_user
Manual phrases:
- login as user
- sign in with credentials
- authenticate with username and password

Code:
`LoginPage.login(String username, String password)`

Example:
```java
loginPage.open();
loginPage.login(username, password);
```

Notes:
- Prefer existing login/session helpers if the repo has them.
- Do not hard-code consultant passwords.

## Navigation

### open_application_area
Manual phrases:
- open module
- navigate to page
- go to screen

Code:
`Navigation.open(String areaName)`

Example:
```java
navigation.open("Orders");
```

Notes:
- If the project has typed navigation methods, prefer those over string navigation.

## Verification

### verify_visible_text
Manual phrases:
- verify message is displayed
- check text appears
- confirm status

Code:
`Assertions.shouldSeeText(String expectedText)`

Example:
```java
assertions.shouldSeeText("Approved");
```

Notes:
- Prefer business-specific assertion helpers when available.
