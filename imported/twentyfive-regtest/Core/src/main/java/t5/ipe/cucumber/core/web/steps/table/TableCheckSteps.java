package t5.ipe.cucumber.core.web.steps.table;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import t5.ipe.cucumber.core.web.DateFormatConverter;
import t5.ipe.cucumber.core.web.TestContext;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.BaseTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.math.RoundingMode;

/**
 * Steps that implements checks of data of table.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class TableCheckSteps {


    @And("^the cell value of the '(.+)' column of the (.+) equals '(.+)' for a row with the following data:$")
    public void checkValueOfTableCell(String columnName, String tableName, String expectedValue, Map<String, String> filter) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, filter);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        Assert.assertEquals(expectedValue, actualCellValue);
    }
    @And("^the cell value of the '(.+)' column of the (.+) is greater than '(.+)' for a row with the following data:$")
    public void theCellValueOfTheREG_CONSULTANTColumnOfTheLaborDualTableIsGreaterThanForARowWithTheFollowingData(String columnName, String tableName, int comparator, Map<String, String> filter) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, filter);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        double actual = Double.parseDouble(actualCellValue.replaceAll(",", "").replaceAll("\\$", "").trim());
        Assert.assertTrue("Actual value is not greater than expected!", actual > comparator);
        AllureUtils.logActionF("Actual value %s is greater than %s!", actual, comparator);
    }

    @And("^the cell value of the '(.+)' column of the (.+) equals '(.+)' for a row with the following data:_-ROUNDOFF CHECK-_$")
    public void checkValueOfTableCellRoundOffCheck(String columnName, String tableName, String expectedValue, Map<String, String> filter) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, filter);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        BigDecimal expected = extractAndParseNumber(expectedValue).setScale(0, RoundingMode.HALF_UP);
        BigDecimal actual = extractAndParseNumber(actualCellValue).setScale(0, RoundingMode.HALF_UP);

        System.out.println("Rounded Expected: " + expected);
        System.out.println("Rounded Actual: " + actual);

        Assert.assertEquals("Rounded values do not match", expected, actual);
        AllureUtils.logActionF("Rounded value matched for %s", actualCellValue);
        System.out.printf("DEBUG: Comparing expected='%s' with actual='%s'%n", expectedValue, actualCellValue);
        TestContext.getInstance().softly.assertThat(actualCellValue)
                .as("Check grand total for column: " + columnName + " in table: " + tableName)
                .isEqualTo(expectedValue);
    }

    @And("^the cell value of the '(.+)' column of the (.+) is checked for a row with the following data:$")
    public void checkIfCellCheckedTableCell(String columnName, String tableName, Map<String, String> filter) {
        boolean isChecked = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .isChecked(columnName, filter);
        AllureUtils.logActionF("Actual Cell Value is %s", isChecked);
        assertTrue("The Cell is un checked",isChecked);
    }

    @And("^the cell value of the '(.+)' column of the (.+) is unchecked for a row with the following data:$")
    public void checkIfCellUnCheckedTableCell(String columnName, String tableName, Map<String, String> filter) {
        boolean isChecked = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .isChecked(columnName, filter);
        AllureUtils.logActionF("Actual Cell Value is %s", isChecked);
        assertFalse("The Cell is checked",isChecked);
    }

    @And("^the cell value of the '(.+)' column of the (.+) equals '(.+)' for a row with the index '(.+)'$")
    public void checkValueOfTableCellByRowIndex(String columnName, String tableName, String expectedValue, String index) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, index);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        Assert.assertEquals(expectedValue, actualCellValue);
    }

    @And("^the cell value of the '(.+)' column of the (.+) equals '(.+)' for a row with the index '(.+)'_-ROUNDOFF CHECK-_$")
    public void checkValueOfTableCellByRowIndexRoundOffCheck(String columnName, String tableName, String expectedValue, String index) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, index);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        BigDecimal expected = extractAndParseNumber(expectedValue).setScale(0, RoundingMode.HALF_UP);
        BigDecimal actual = extractAndParseNumber(actualCellValue).setScale(0, RoundingMode.HALF_UP);

        System.out.println("Rounded Expected: " + expected);
        System.out.println("Rounded Actual: " + actual);

        Assert.assertEquals("Rounded values do not match", expected, actual);
        AllureUtils.logActionF("Rounded value matched for %s", actualCellValue);
        System.out.printf("DEBUG: Comparing expected='%s' with actual='%s'%n", expectedValue, actualCellValue);
        TestContext.getInstance().softly.assertThat(actualCellValue)
                .as("Check grand total for column: " + columnName + " in table: " + tableName)
                .isEqualTo(expectedValue);
    }

    @And("^Grand total cell value of the '(.+)' column of the (.+) equals '(.+)'$")
    public void grandTotalCheckValueOfTableCellByRowIndex(String columnName, String tableName, String expectedValue) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        Assert.assertEquals(expectedValue, actualCellValue);
    }

    @And("^Grand total cell value of the '(.+)' column of the (.+) equals '(.+)'_-Warning-_$")
    public void grandTotalCheckValueOfTableCellByRowIndexWarning(String columnName, String tableName, String expectedValue) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
        TestContext.getInstance().softly.assertThat(actualCellValue).isEqualTo(expectedValue);
//        Assert.assertEquals(expectedValue, actualCellValue);
    }

    public static BigDecimal extractAndParseNumber(String value) {
        if (value == null || value.isEmpty()) return BigDecimal.ZERO;

        String cleaned = value.trim().replaceAll(",", "");
        String[] parts = cleaned.split("[^\\d.-]+"); // split on non-numeric parts
        for (String part : parts) {
            if (!part.isEmpty()) {
                return new BigDecimal(part);
            }
        }
        throw new NumberFormatException("No numeric part found in: " + value);
    }

    @And("^Grand total cell value of the '(.+)' column of the (.+) equals '(.+)'_-ROUNDOFF CHECK-_$")
    public void grandTotalCheckValueOfTableCellByRowIndexRoundOffCheck(String columnName, String tableName, String expectedValue) {
        String actualCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName);
        AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
//        System.out.println("ActualCellValue (with chars):");
//        for (char c : actualCellValue.toCharArray()) {
//            System.out.printf("'%c' -> %d%n", c, (int)c);
//        }
        BigDecimal expected = extractAndParseNumber(expectedValue).setScale(0, RoundingMode.HALF_UP);
        BigDecimal actual = extractAndParseNumber(actualCellValue).setScale(0, RoundingMode.HALF_UP);

        System.out.println("Rounded Expected: " + expected);
        System.out.println("Rounded Actual: " + actual);

        Assert.assertEquals("Rounded values do not match", expected, actual);
        AllureUtils.logActionF("Rounded value matched for %s", actualCellValue);
        System.out.printf("DEBUG: Comparing expected='%s' with actual='%s'%n", expectedValue, actualCellValue);
        TestContext.getInstance().softly.assertThat(actualCellValue)
                .as("Check grand total for column: " + columnName + " in table: " + tableName)
                .isEqualTo(expectedValue);
    }

    @And("^number of rows in (.*) equals (.*)$")
    public void checkRowsCount(String tableName, int expectedCount) {
        BaseTable table = SearchUtils.findElementAtCurrentPage(tableName, BaseTable.class);
        Assert.assertEquals(expectedCount, table.getRowsCount());
        AllureUtils.logActionF("Number of rows in %s equals %s", tableName, String.valueOf(table.getRowsCount()));
    }

    @And("^(.+) contain row with following data:")
    public void checkRowPresence(String tableName, Map<String, String> filter) {

        ReadableTable costingVersionsTable = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class);

        assertTrue(String.format("%s does not contain row with following data %s", tableName, filter.toString()), costingVersionsTable.isRowPresent(filter));
        AllureUtils.logActionF("%s contain row with following data %s", tableName, filter.toString());

    }

    @And("^(.+) contain row with following data_-Warning-_:")
    public void checkWarningRowPresence(String tableName, Map<String, String> filter) {
        ReadableTable costingVersionsTable;

        try {
            costingVersionsTable = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class);
        } catch (NoSuchElementException exception) {
            TestContext.getInstance().softly.fail(
                    String.format("❗ Table [%s] not found on the current page: %s", tableName, exception.getMessage())
            );
            return; // Prevent NullPointerException in the next line
        }
        boolean rowPresent;

        try {
            rowPresent = costingVersionsTable.isRowPresent(filter);
        } catch (Exception e) {
            rowPresent = false;
        }
        TestContext.getInstance().softly
                .assertThat(rowPresent)
                .as("%s does not contain row with following data %s", tableName, filter.toString())
                .isTrue();

        AllureUtils.logActionF("%s contains row with following data %s", tableName, filter.toString());
    }


    @Then("^I should see the following columns in the (.+):")
    public void iShouldSeeTheFollowingColumnsInTheTable(String tableName, DataTable dataTable) {
//        String lastColumnXpath = "";
        String columnXpath = "";

        if(Objects.equals(tableName, "'WBS' table") || Objects.equals(tableName, "'Labor' table") || Objects.equals(tableName, "'Billing Items' table")){
//            lastColumnXpath = "//*[@role='columnheader' and @aria-hidden='false'][last()]//*[@class='x-column-header-text-inner']";
                columnXpath = "//*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-text-inner']";
        } else if (Objects.equals(tableName, "'Amortization Schedule' table")) {
            columnXpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-text-inner']";
        }else if (Objects.equals(tableName, "'Escalation and Inflammation Rates' table")) {
            columnXpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//*[@role='tabpanel' and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
        }else if (Objects.equals(tableName, "'Billing Items Cost Allocation' table")){
            columnXpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='rowgroup' and @aria-hidden='false']//*[@role='columnheader' and @aria-hidden='false']//*[@class='x-column-header-text-inner']";
        } else{
            Assert.fail("Please Specify column xpath in method");
        }
        List<String> expectedColumns = dataTable.asList();

        // Collect visible columns using the provided XPath
        ElementsCollection columnElements = $$x(columnXpath);
        System.out.println("Column Elements:");

        for (int i = 0; i < columnElements.size(); i++) {
            SelenideElement element = columnElements.get(i).scrollIntoView(true);
            String text = element.getText().trim();

            // Construct XPath by appending position (1-based index)
            String constructedXpath = String.format("(%s)[%d]", columnXpath, i + 1);

            System.out.printf("Column %d: Text='%s', XPath=%s%n", i + 1, text, constructedXpath);
        }
//        List<String> initialColumns = new ArrayList<String>();
//        for (SelenideElement element : initialColumnElements) {
//            initialColumns.add(element.getText().trim());
//        }
//        Set<String> actualColumns = new LinkedHashSet<>(initialColumns);
//
//        $x(lastColumnXpath).scrollIntoView(true);
//
//        ElementsCollection finalColumnElements = $$x(columnXpath);

        List<String> actualColumns = new ArrayList<String>();
        System.out.println(columnElements.size());
        for (SelenideElement element : columnElements) {
            String text = element.scrollIntoView(true).getText().trim();
            System.out.printf("\nColumn Name '%s' adding to actual Columns", text);
            actualColumns.add(text);
        }
//        actualColumns.addAll(finalColumns);
        // Print actual and expected columns
        AllureUtils.logActionF("\nExpected Columns:");
        expectedColumns.forEach(col -> AllureUtils.logActionF(" - %s", col));

        AllureUtils.logActionF("\nActual Columns:");
        actualColumns.forEach(col -> AllureUtils.logActionF(" - %s", col));

        // Assertion: Check that all expected columns are present
        for (String expected : expectedColumns) {
            assertTrue("Missing column: " + expected, actualColumns.contains(expected));
        }
    }

    @Then("^I verify column (.+) distribution between dates '(.+)' and '(.+)'$")
    public void iVerifyColumnDistributionBetweenDates(String distribution, String startDateStr, String endDateStr) {
        Selenide.sleep(3000);
        String cellType = distribution.split(" by ")[0];
        String columnType = distribution.split(" by ")[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<String> periods = new ArrayList<>();

        if (columnType.equalsIgnoreCase("Week")) {
            String firstDate = $$x("//*[@role='columnheader' and @aria-hidden='false' and contains(@class,'x-iBEDurationColumnCls')]//*[@data-ref='textInnerEl' and not(text()='Duration') and not(text()='Planned Effort') and not(text()='Effort')]").first().scrollIntoView(true).shouldBe(Condition.visible, Duration.ofSeconds(7)).getText();
            LocalDate firstColumnDate = LocalDate.parse(firstDate, formatter);
            // Align to the first Monday on or after the start date
            while (startDate.getDayOfWeek() != firstColumnDate.getDayOfWeek()) {
                startDate = startDate.minusDays(1);
            }

            while (!startDate.isAfter(endDate)) {
                periods.add(startDate.format(outputFormatter));
                startDate = startDate.plusWeeks(1);
            }

        } else if (columnType.equalsIgnoreCase("Month")) {
            YearMonth startMonth = YearMonth.from(startDate);
            YearMonth endMonth = YearMonth.from(endDate);

            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.ENGLISH);

            while (!startMonth.isAfter(endMonth)) {
                periods.add(startMonth.format(monthFormatter));  // Format: Jan-26
                startMonth = startMonth.plusMonths(1);
            }
        }

        System.out.println("Generated " + columnType + " list:");
        for (String period : periods) {
            System.out.println(period);
        }

        ElementsCollection headers = $$x("//*[@role='columnheader' and @aria-hidden='false' and contains(@class,'x-iBEDurationColumnCls')]//*[@data-ref='textInnerEl' and not(text()='Duration') and not(text()='Planned Effort') and not(text()='Effort')]");

        List<String> actualColumns = new ArrayList<String>();
        for (int i = 0; i < headers.size(); i++) {
            ElementsCollection headersReinitialized = $$x("//*[@role='columnheader' and @aria-hidden='false' and contains(@class,'x-iBEDurationColumnCls')]//*[@data-ref='textInnerEl' and not(text()='Duration') and not(text()='Planned Effort') and not(text()='Effort')]");
            SelenideElement currentElement = headersReinitialized.get(i); // Re-fetch each time
            actualColumns.add(currentElement.scrollIntoView(true).getText().trim());
        }
        List<String> expected = DateFormatConverter.convertDates(periods);
        List<String> actual = DateFormatConverter.convertDates(actualColumns);
        Assert.assertEquals("Distribution is not as expected",expected,actual);
    }

    @And("^I verify effort values in (.+) are empty for rows with data:$")
    public void iVerifyEffortValuesInLaborDualTableAreEmptyForRowsWithData(String tableName, Map<String, String> filter) {
        int laborRowNumber = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class).getRowIndex(filter) +1;
        ElementsCollection effortCells = $$x("//*[@role='grid' and contains(@class,'x-fit-item')and @aria-hidden='false']//*[contains(@class,'x-grid-scrollbar-clipper') and not(contains(@class,'x-grid-scrollbar-clipper-locked'))]//table["+laborRowNumber+"]//tr/td[contains(@class,'isGeneratedPlanColumn')]//*[contains(@class,'x-grid-cell-inner')]");
        // Extract texts and check if all are null, empty, or whitespace-only
        boolean allEmpty = effortCells.stream()
                .map(SelenideElement::getText)   // or .getAttribute("textContent")
                .allMatch(text -> text == null || text.trim().isEmpty());
        Assert.assertTrue("Some elements are not empty, null, or whitespace-only!", allEmpty);
        AllureUtils.logActionF("All effort values in %s are empty for rows with data %s", tableName, filter.toString());
    }

    @Then("I verify that all rows in {string} table are selected")
    public void iVerifyThatAllRowsInSelectContractLinesToImportTableAreSelected(String tableName) {
        int rowsCount = $$x("//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//table").size();
        int selectedRowsCount = $$x("//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//table[contains(@class,'x-grid-item-selected')]").size();
        Assert.assertEquals("Not all rows are selected!", rowsCount, selectedRowsCount);
        AllureUtils.saveScreenshot();
        AllureUtils.logActionF("All %s rows are selected in %s table", String.valueOf(rowsCount), tableName);
    }

    @And("^(.+) is expanded$")
    public void proposalBOMTableIsExpanded(String tableName) {
        BaseTable table = SearchUtils.findElementAtCurrentPage(tableName, BaseTable.class);
        Assert.assertTrue("Table is not expanded!", table.getRowsCount() > 1);
        ElementsCollection expandedRows =
                $$x("//*[@class='  x-grid-row' and @aria-expanded='false']");
        Assert.assertEquals("Expected no collapsed rows", 0, expandedRows.size());
    }
}
