#!/usr/bin/env python3
"""
Automated Test Suite for Master Data - Products & Services
Test ID: TC-MD-PS-001
Framework: Selenium WebDriver
Base URL: https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/
"""

import time
import json
import os
from datetime import datetime
from pathlib import Path

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.action_chains import ActionChains
from selenium.common.exceptions import TimeoutException, NoSuchElementException

# Configuration
BASE_URL = 'https://app-twenty5ipe-lm-dev.cfapps.us10.hana.ondemand.com/'
SCREENSHOT_DIR = './test-screenshots'
REPORT_FILE = './test-report.json'
WAIT_TIMEOUT = 10

# Test report structure
test_report = {
    'timestamp': datetime.now().isoformat(),
    'baseUrl': BASE_URL,
    'results': [],
    'summary': {
        'totalTests': 0,
        'passed': 0,
        'failed': 0,
        'skipped': 0
    }
}


class TestAutomation:
    def __init__(self):
        """Initialize the test automation class"""
        self.setup_directories()
        self.driver = None
        self.wait = None
        self.actions = None

    def setup_directories(self):
        """Create necessary directories for screenshots and reports"""
        Path(SCREENSHOT_DIR).mkdir(exist_ok=True)

    def init_driver(self):
        """Initialize WebDriver"""
        print("Initializing WebDriver...")
        options = webdriver.ChromeOptions()
        # options.add_argument('--headless')  # Comment out to see browser
        options.add_argument('--no-sandbox')
        options.add_argument('--disable-dev-shm-usage')
        options.add_argument('--disable-blink-features=AutomationControlled')
        options.add_argument('user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36')

        self.driver = webdriver.Chrome(options=options)
        self.wait = WebDriverWait(self.driver, WAIT_TIMEOUT)
        self.actions = ActionChains(self.driver)
        self.driver.set_window_size(1920, 1080)

    def take_screenshot(self, test_name, step_name):
        """Take a screenshot and save it"""
        timestamp = int(time.time() * 1000)
        filename = f"{test_name}-{step_name.replace(' ', '_')}-{timestamp}.png"
        filepath = os.path.join(SCREENSHOT_DIR, filename)
        self.driver.save_screenshot(filepath)
        return filepath

    def log_result(self, test_id, test_name, step_name, passed, message, screenshot=None):
        """Log test result"""
        result = {
            'testId': test_id,
            'testName': test_name,
            'stepName': step_name,
            'passed': passed,
            'message': message,
            'timestamp': datetime.now().isoformat(),
            'screenshot': screenshot
        }
        test_report['results'].append(result)
        test_report['summary']['totalTests'] += 1
        if passed:
            test_report['summary']['passed'] += 1
            print(f"[✓ PASS] {test_id} - {step_name}: {message}")
        else:
            test_report['summary']['failed'] += 1
            print(f"[✗ FAIL] {test_id} - {step_name}: {message}")

    def find_element_by_text(self, text, tag_name='*'):
        """Find element by visible text"""
        try:
            xpath = f"//{tag_name}[contains(text(), '{text}')]"
            return self.driver.find_element(By.XPATH, xpath)
        except NoSuchElementException:
            return None

    def find_elements_by_text(self, text_list):
        """Find multiple elements by text"""
        found = []
        for text in text_list:
            element = self.find_element_by_text(text)
            if element:
                found.append(text)
        return found

    def hover_element(self, element):
        """Hover over an element"""
        self.actions.move_to_element(element).perform()
        time.sleep(0.5)

    def test_case_1(self):
        """Test Case 1: Navigate to Master Data Tab and Verify Sub-tabs"""
        test_id = 'TC-MD-PS-001.1'
        test_name = 'Navigate to Master Data Tab'

        print(f"\n▶ Running {test_id}: {test_name}")

        try:
            # Step 1: Navigate to the application
            print("  Step 1: Navigating to application...")
            self.driver.get(BASE_URL)
            time.sleep(2)
            screenshot = self.take_screenshot(test_id, 'initial-load')
            self.log_result(test_id, test_name, 'Page loaded', True, 'Application loaded successfully', screenshot)

            # Step 2: Look for Master Data tab/menu
            print("  Step 2: Looking for Master Data tab...")
            master_data_element = self.find_element_by_text('Master Data')

            if master_data_element:
                master_data_element.click()
                time.sleep(1)
                screenshot = self.take_screenshot(test_id, 'master-data-clicked')
                self.log_result(test_id, test_name, 'Master Data Tab Clicked', True,
                              'Master Data tab clicked successfully', screenshot)
            else:
                screenshot = self.take_screenshot(test_id, 'master-data-not-found')
                self.log_result(test_id, test_name, 'Master Data Tab Found', False,
                              'Could not locate Master Data tab', screenshot)
                return False

            # Step 3: Verify sub-tabs are displayed
            print("  Step 3: Verifying sub-tabs...")
            products_element = self.find_element_by_text('Products & Services')
            screenshot = self.take_screenshot(test_id, 'subtabs-verification')

            if products_element:
                self.log_result(test_id, test_name, 'Sub-tabs Visible', True,
                              'Sub-tabs are displayed', screenshot)
                return True
            else:
                self.log_result(test_id, test_name, 'Sub-tabs Visible', False,
                              'Sub-tabs not found', screenshot)
                return False

        except Exception as error:
            screenshot = self.take_screenshot(test_id, 'error')
            self.log_result(test_id, test_name, 'Test Execution', False,
                          f'Error: {str(error)}', screenshot)
            return False

    def test_case_2(self):
        """Test Case 2: Navigate to Products & Services Page"""
        test_id = 'TC-MD-PS-001.2'
        test_name = 'Navigate to Products & Services Page'

        print(f"\n▶ Running {test_id}: {test_name}")

        try:
            # Step 1: Click on Products & Services
            print("  Step 1: Clicking Products & Services...")
            ps_element = self.find_element_by_text('Products & Services')

            if not ps_element:
                ps_element = self.find_element_by_text('Products / Services')

            if ps_element:
                ps_element.click()
                time.sleep(2)
                screenshot = self.take_screenshot(test_id, 'products-page')
                self.log_result(test_id, test_name, 'Page Navigated', True,
                              'Navigated to Products & Services page', screenshot)
            else:
                screenshot = self.take_screenshot(test_id, 'products-not-found')
                self.log_result(test_id, test_name, 'Products & Services Click', False,
                              'Could not locate Products & Services option', screenshot)
                return False

            # Step 2: Verify URL
            print("  Step 2: Verifying URL...")
            current_url = self.driver.current_url
            url_valid = 'product' in current_url.lower() or 'service' in current_url.lower()
            self.log_result(test_id, test_name, 'URL Verification', url_valid,
                          f'Current URL: {current_url}', screenshot)

            return True

        except Exception as error:
            screenshot = self.take_screenshot(test_id, 'error')
            self.log_result(test_id, test_name, 'Test Execution', False,
                          f'Error: {str(error)}', screenshot)
            return False

    def test_case_3(self):
        """Test Case 3: Verify New Button Options"""
        test_id = 'TC-MD-PS-001.3'
        test_name = 'Verify New Button Options'

        print(f"\n▶ Running {test_id}: {test_name}")

        try:
            # Step 1: Find New button
            print("  Step 1: Locating New button...")
            new_button = self.find_element_by_text('New', 'button')

            if not new_button:
                new_button = self.find_element_by_text('New')

            if not new_button:
                screenshot = self.take_screenshot(test_id, 'new-button-not-found')
                self.log_result(test_id, test_name, 'New Button Found', False,
                              'Could not locate New button', screenshot)
                return False

            # Step 2: Hover over New button
            print("  Step 2: Hovering over New button...")
            self.hover_element(new_button)
            screenshot = self.take_screenshot(test_id, 'new-button-hovered')
            self.log_result(test_id, test_name, 'New Button Hovered', True,
                          'Hovered over New button', screenshot)

            # Step 3: Verify dropdown options
            print("  Step 3: Verifying dropdown options...")
            expected_options = [
                'Create from Template',
                'Create from Type',
                'Create Template',
                'Edit Template'
            ]

            found_options = self.find_elements_by_text(expected_options)
            screenshot = self.take_screenshot(test_id, 'dropdown-options')

            all_found = len(found_options) == len(expected_options)
            self.log_result(test_id, test_name, 'Dropdown Options Verified', all_found,
                          f"Found {len(found_options)}/{len(expected_options)} options: {', '.join(found_options)}",
                          screenshot)

            return all_found

        except Exception as error:
            screenshot = self.take_screenshot(test_id, 'error')
            self.log_result(test_id, test_name, 'Test Execution', False,
                          f'Error: {str(error)}', screenshot)
            return False

    def test_case_4(self):
        """Test Case 4: Verify Create from Type Additional Options"""
        test_id = 'TC-MD-PS-001.4'
        test_name = 'Verify Additional Options on Create from Type'

        print(f"\n▶ Running {test_id}: {test_name}")

        try:
            # Step 1: Hover over Create from Type
            print("  Step 1: Hovering over Create from Type...")
            create_from_type = self.find_element_by_text('Create from Type')

            if not create_from_type:
                screenshot = self.take_screenshot(test_id, 'create-from-type-not-found')
                self.log_result(test_id, test_name, 'Create from Type Found', False,
                              'Could not locate Create from Type option', screenshot)
                return False

            self.hover_element(create_from_type)
            screenshot = self.take_screenshot(test_id, 'create-from-type-hovered')
            self.log_result(test_id, test_name, 'Create from Type Hovered', True,
                          'Hovered over Create from Type option', screenshot)

            # Step 2: Verify Purchased Part option
            print("  Step 2: Verifying Purchased Part option...")
            purchased_part = self.find_element_by_text('Purchased Part')
            screenshot = self.take_screenshot(test_id, 'submenu-options')

            if purchased_part:
                self.log_result(test_id, test_name, 'Purchased Part Option Found', True,
                              'Purchased Part option is visible', screenshot)
                return True
            else:
                self.log_result(test_id, test_name, 'Purchased Part Option Found', False,
                              'Purchased Part option not found', screenshot)
                return False

        except Exception as error:
            screenshot = self.take_screenshot(test_id, 'error')
            self.log_result(test_id, test_name, 'Test Execution', False,
                          f'Error: {str(error)}', screenshot)
            return False

    def test_case_5(self):
        """Test Case 5: Create Purchased Part and Verify Sub-tabs"""
        test_id = 'TC-MD-PS-001.5'
        test_name = 'Create Purchased Part and Verify Sub-tabs'

        print(f"\n▶ Running {test_id}: {test_name}")

        try:
            # Step 1: Click Purchased Part
            print("  Step 1: Clicking Purchased Part...")
            purchased_part = self.find_element_by_text('Purchased Part')

            if purchased_part:
                purchased_part.click()
                time.sleep(2)
                screenshot = self.take_screenshot(test_id, 'create-page-loaded')
                self.log_result(test_id, test_name, 'Create Page Loaded', True,
                              'Navigated to Create Purchased Part page', screenshot)
            else:
                screenshot = self.take_screenshot(test_id, 'purchased-part-not-found')
                self.log_result(test_id, test_name, 'Purchased Part Clicked', False,
                              'Could not locate or click Purchased Part', screenshot)
                return False

            # Step 2: Verify required sub-tabs
            print("  Step 2: Verifying sub-tabs...")
            required_subtabs = [
                'Key Info',
                'Product Management',
                'Planning & Estimating',
                'Cost & Prices',
                'Supplier Sources',
                'Comments & Files',
                'Texts'
            ]

            found_subtabs = self.find_elements_by_text(required_subtabs)
            screenshot = self.take_screenshot(test_id, 'subtabs-verification')

            all_found = len(found_subtabs) == len(required_subtabs)
            self.log_result(test_id, test_name, 'Sub-tabs Verified', all_found,
                          f"Found {len(found_subtabs)}/{len(required_subtabs)} sub-tabs: {', '.join(found_subtabs)}",
                          screenshot)

            return all_found

        except Exception as error:
            screenshot = self.take_screenshot(test_id, 'error')
            self.log_result(test_id, test_name, 'Test Execution', False,
                          f'Error: {str(error)}', screenshot)
            return False

    def run_all_tests(self):
        """Run all test cases"""
        print("=" * 50)
        print("Master Data - Products & Services Tests")
        print("=" * 50)
        print(f"Base URL: {BASE_URL}")
        print(f"Timestamp: {datetime.now().isoformat()}\n")

        try:
            self.init_driver()

            # Run test cases
            test1_result = self.test_case_1()
            test2_result = self.test_case_2()
            test3_result = self.test_case_3()
            test4_result = self.test_case_4()
            test5_result = self.test_case_5()

            # Generate report
            print("\n" + "=" * 50)
            print("Test Summary")
            print("=" * 50)
            print(f"Total Tests: {test_report['summary']['totalTests']}")
            print(f"Passed: {test_report['summary']['passed']}")
            print(f"Failed: {test_report['summary']['failed']}")
            if test_report['summary']['totalTests'] > 0:
                pass_rate = (test_report['summary']['passed'] / test_report['summary']['totalTests']) * 100
                print(f"Pass Rate: {pass_rate:.2f}%")

            # Save report
            with open(REPORT_FILE, 'w') as f:
                json.dump(test_report, f, indent=2)

            print(f"\nTest report saved to: {REPORT_FILE}")
            print(f"Screenshots saved to: {SCREENSHOT_DIR}")

        except Exception as error:
            print(f"Test execution failed: {error}")
        finally:
            if self.driver:
                self.driver.quit()


if __name__ == '__main__':
    test_automation = TestAutomation()
    test_automation.run_all_tests()
