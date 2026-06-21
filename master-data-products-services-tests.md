# Test Case: Master Data - Products & Services Management

**Document ID:** TC-MD-PS-001  
**Module:** Master Data  
**Feature:** Products & Services Management  
**Base URL:** https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/  
**Last Updated:** 2026-06-21  
**Status:** Ready for Execution

---

## Test Case 1: Navigate to Master Data Tab and Verify Sub-tabs

**Test ID:** TC-MD-PS-001.1  
**Priority:** High  
**Test Type:** Functional  
**Precondition:** User is logged in and on the application homepage

### Test Steps:

| Step | Action | Expected Result | Status |
|------|--------|-----------------|--------|
| 1 | Click on the "Master Data" tab in the main navigation | Master Data tab is activated and becomes the active section | ⭕ Pending |
| 2 | Verify that sub-tab options are displayed below Master Data | Sub-tabs should be visible including at least "Products & Services", "Purchasing Info", and other related options | ⭕ Pending |

### Assertions:
- [ ] Master Data tab is highlighted/active
- [ ] Sub-tab menu is visible
- [ ] At least 3 sub-tabs are displayed
- [ ] "Products & Services" option is present in the sub-tabs

### Pass Criteria:
All assertions pass and sub-tabs are clearly visible and clickable.

---

## Test Case 2: Navigate to Products & Services Page

**Test ID:** TC-MD-PS-001.2  
**Priority:** High  
**Test Type:** Functional  
**Precondition:** Master Data tab is open and sub-tabs are visible

### Test Steps:

| Step | Action | Expected Result | Status |
|------|--------|-----------------|--------|
| 1 | Click on "Products & Services" option from the sub-tabs | User is redirected to the Products & Services page | ⭕ Pending |
| 2 | Verify page load and URL | Page loads successfully with correct URL containing "products" or "services" | ⭕ Pending |
| 3 | Verify page title/header | Page header shows "Products & Services" or similar title | ⭕ Pending |

### Assertions:
- [ ] User is redirected to Products & Services page
- [ ] URL contains "/products" or "/services" (or similar)
- [ ] Page header/title displays "Products & Services"
- [ ] Page loads without errors
- [ ] Navigation bar shows Products & Services as active

### Pass Criteria:
Page successfully loads and displays Products & Services content with correct navigation indication.

---

## Test Case 3: Verify New Button Options

**Test ID:** TC-MD-PS-001.3  
**Priority:** High  
**Test Type:** Functional  
**Precondition:** User is on the Products & Services page

### Test Steps:

| Step | Action | Expected Result | Status |
|------|--------|-----------------|--------|
| 1 | Locate the "New" button on the Products & Services page | New button is visible and easily accessible | ⭕ Pending |
| 2 | Hover over the "New" button | A dropdown menu appears with options | ⭕ Pending |
| 3 | Verify all dropdown options are displayed | The dropdown should contain 4 options: "Create from Template", "Create from Type", "Create Template", and "Edit Template" | ⭕ Pending |

### Assertions:
- [ ] New button is visible on the page
- [ ] New button is clickable
- [ ] Hovering over New button displays a dropdown menu
- [ ] Dropdown menu contains exactly 4 options
- [ ] "Create from Template" option is present
- [ ] "Create from Type" option is present
- [ ] "Create Template" option is present
- [ ] "Edit Template" option is present
- [ ] All options are visible and readable

### Pass Criteria:
New button dropdown displays all 4 required options clearly and they are accessible.

---

## Test Case 4: Verify Additional Options on Create from Type Hover

**Test ID:** TC-MD-PS-001.4  
**Priority:** High  
**Test Type:** Functional  
**Precondition:** New button dropdown is visible with "Create from Type" option displayed

### Test Steps:

| Step | Action | Expected Result | Status |
|------|--------|-----------------|--------|
| 1 | From the New button dropdown, hover over "Create from Type" option | A submenu appears with additional options | ⭕ Pending |
| 2 | Verify submenu options are displayed | Submenu should show product type options including at least "Purchased Part" | ⭕ Pending |
| 3 | Verify "Purchased Part" option is visible in the submenu | "Purchased Part" option is clearly visible and clickable | ⭕ Pending |

### Assertions:
- [ ] Hovering on "Create from Type" reveals a submenu
- [ ] Submenu contains at least 1 option
- [ ] "Purchased Part" option is present in the submenu
- [ ] All submenu options are visible and readable
- [ ] Submenu options are clickable

### Pass Criteria:
Submenu displays correctly with "Purchased Part" and other product type options available.

---

## Test Case 5: Create Purchased Part and Verify Sub-tabs

**Test ID:** TC-MD-PS-001.5  
**Priority:** High  
**Test Type:** Functional  
**Precondition:** "Create from Type" submenu is visible with "Purchased Part" option displayed

### Test Steps:

| Step | Action | Expected Result | Status |
|------|--------|-----------------|--------|
| 1 | Click on "Purchased Part" option from the submenu | User is redirected to the "Create Purchased Part" page | ⭕ Pending |
| 2 | Verify page title/header | Page header displays "Create Purchased Part" or similar title | ⭕ Pending |
| 3 | Verify all required sub-tabs are displayed | The following sub-tabs should be visible: "Key Info", "Product Management", "Planning & Estimating", "Cost & Prices", "Supplier Sources", "Comments & Files", "Texts" | ⭕ Pending |
| 4 | Verify each sub-tab is accessible | Click on each sub-tab to verify it loads content without errors | ⭕ Pending |

### Assertions:
- [ ] User is redirected to Create Purchased Part page
- [ ] Page title/header shows "Create Purchased Part"
- [ ] URL contains "/create" or "/new" and "purchased" or "part"
- [ ] All 7 sub-tabs are visible:
  - [ ] Key Info
  - [ ] Product Management
  - [ ] Planning & Estimating
  - [ ] Cost & Prices
  - [ ] Supplier Sources
  - [ ] Comments & Files
  - [ ] Texts
- [ ] Each sub-tab is clickable
- [ ] Clicking each sub-tab loads content without errors
- [ ] Default sub-tab (typically Key Info) is active on page load

### Pass Criteria:
Create Purchased Part page loads successfully with all 7 sub-tabs visible and accessible. Each sub-tab can be clicked and loads content without errors.

---

## Test Summary

| Test Case ID | Test Case Name | Status | Pass/Fail | Notes |
|--------------|----------------|--------|-----------|-------|
| TC-MD-PS-001.1 | Navigate to Master Data Tab and Verify Sub-tabs | ⭕ Pending | - | - |
| TC-MD-PS-001.2 | Navigate to Products & Services Page | ⭕ Pending | - | - |
| TC-MD-PS-001.3 | Verify New Button Options | ⭕ Pending | - | - |
| TC-MD-PS-001.4 | Verify Additional Options on Create from Type Hover | ⭕ Pending | - | - |
| TC-MD-PS-001.5 | Create Purchased Part and Verify Sub-tabs | ⭕ Pending | - | - |

---

## Test Environment

**Base URL:** https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/  
**Environment:** Development  
**Browser:** Chrome/Firefox/Safari  
**Screen Resolution:** 1920x1080 (Standard Desktop)  
**Test Data Required:** Valid user credentials with access to Master Data module

---

## Notes

- All tests require a logged-in user with appropriate permissions
- Tests should be executed in sequence as listed
- Wait for page loads to complete before proceeding with next step
- Clear browser cache between test runs if issues occur
- Screenshots should be captured for failed assertions for evidence
