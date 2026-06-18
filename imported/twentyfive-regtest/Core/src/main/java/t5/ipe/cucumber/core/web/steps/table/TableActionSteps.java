package t5.ipe.cucumber.core.web.steps.table;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import org.junit.Assert;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.BaseTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.web.SearchUtils;
import io.cucumber.java.en.And;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

/**
 * Steps that implements actions aimed at interaction with tables.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class TableActionSteps {

    @And("^(?:I |)set value '(.+)' to the cell of the column '(.+)' of the (.+) for the row with the following data:$")
    public void setValueToTableCell(String value, String columnName, String tableName, Map<String, String> filter) {
        SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                .setCellValue(value, columnName, filter);
    }

    @And("^(?:I |)set value '(.+)' to the cell of the column '(.+)' of the (.+) for the row with the index '(.+)'")
    public void setValueToTableCell(String value, String columnName, String tableName, int rowIndex) {
        SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                .setCellValue(value, columnName, rowIndex);
    }

    @And("^(?:I |)delete value from the cell of the column '(.+)' of the (.+) for the row with the following data:$")
    public void deleteValueToTableCell(String columnName, String tableName, Map<String, String> filter) {
        SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                .deleteCellValue(columnName, filter);
    }


    @And("^(?:I |)fill new row of the (.+) table with following data:$")
    public void fillNewRow(String tableName, Map<String, String> attributes) {
        SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                .fillNewRow(attributes);
    }

    @And("^(?:I |)fill selected row of table (.+) with following data:$")
    public void fillSelectedRow(String tableName, Map<String, String> attributes) {
        SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                .fillSelectedRow(attributes);
    }

    @And("^(?:I |)deactivate toggle '(.+)' in table (.+) for row with data:$")
    public void checkToggleInTable(String columnName, String tableName, Map<String, String> rowFilter) {
        SearchUtils.findElementAtCurrentPage(tableName, CheckableTable.class).uncheckCell(columnName, rowFilter);
    }

    @And("^(?:I |)activate checkbox '(.+)' in table (.+) for row with data:$")
    public void activateCheckBoxInTable(String columnName, String tableName, Map<String, String> rowFilter) {
        boolean isChecked = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .isChecked(columnName, rowFilter);
        if (!isChecked) {
        try {
            SearchUtils.findElementAtCurrentPage(tableName, CheckableTable.class).checkCell(columnName, rowFilter);
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
           }
        }
        else {
            Allure.step(String.format("Checkbox '%s' is already activated for the row with data: %s", columnName, rowFilter.toString()));
        }
    }

    @And("^(?:I |)click '(.+)' in table (.+) for row with data:$")
    public void clickElementInTable(String columnName, String tableName, Map<String, String> rowFilter) {
        try {
            SearchUtils.findElementAtCurrentPage(tableName, CheckableTable.class).checkCell(columnName, rowFilter);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @And("^(?:I |)click '(.+)' in table (.+) in row number (\\d+)$")
    public void clickElementInTable(String columnName, String tableName, int index) {
        SearchUtils.findElementAtCurrentPage(tableName, CheckableTable.class).checkCell(columnName, index);
    }

    @And("^(?:I |)hover '(.+)' cell in table (.+) and click ⚙\uFE0F for row with data:$")
    public void hoverElementInTable(String columnName, String tableName, Map<String, String> rowFilter) {
        SearchUtils.findElementAtCurrentPage(tableName, BaseTable.class).hoverCellAndClickGearMenu(columnName, rowFilter);
    }

    public static double parseDollar(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string is null or empty");
        }

        // Remove dollar sign, commas, and whitespace
        String cleaned = input.replaceAll("[$,\\s]", "");

        return Double.parseDouble(cleaned);
    }


    @And("^(?:I |)set '(.+)' to '(.+)' in (.+) and verify '(.+)' for a row with the index '(.+)'$")
    public void iSetCostToInBillingItemsCostAllocationTableAndVerifyCurrentCost(String columnName, String value, String tableName, String columnNameVerify, String index) {
        String initialEditCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnName, index);
        String initialVerifyCellValue = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                .readCellValue(columnNameVerify, index);
        assertNotNull( columnNameVerify+"should not be null", initialVerifyCellValue);
        assertFalse(columnNameVerify+"should not be empty", initialVerifyCellValue.isEmpty());
        assertFalse(columnNameVerify+"should not be blank", initialVerifyCellValue.trim().isEmpty());
        if(columnName.equals("% Cost")) {
            double baseValue = parseDollar(initialVerifyCellValue);
            double percent = Double.parseDouble(initialEditCellValue.replace("%", ""));
            double finalPercent = Double.parseDouble(value.replace("%", ""));
            double totalValue = (baseValue / percent) * 100;
            double expectedVerifyCellValueDouble = (totalValue * finalPercent) / 100;
            BigDecimal expectedVerifyCellValue = new BigDecimal(String.valueOf(expectedVerifyCellValueDouble)).setScale(2, RoundingMode.DOWN);
            AllureUtils.logActionF("Expected Value is %s", expectedVerifyCellValue);
            SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class).deleteCellValue(columnName, Integer.parseInt(index));
            SearchUtils.findElementAtCurrentPage(tableName, EditableTable.class)
                    .setCellValue(value, columnName, Integer.parseInt(index));
            Selenide.sleep(3000);
            String actualCellValueString = SearchUtils.findElementAtCurrentPage(tableName, ReadableTable.class)
                    .readCellValue(columnNameVerify, index);
            BigDecimal actualCellValue = BigDecimal.valueOf(parseDollar(actualCellValueString)).setScale(2, RoundingMode.DOWN);

            AllureUtils.logActionF("Actual Cell Value is %s", actualCellValue);
            BigDecimal difference = expectedVerifyCellValue.subtract(actualCellValue).abs();
            Assert.assertTrue(
                    "Values differ more than 0.01. Expected: " + expectedVerifyCellValue + ", Actual: " + actualCellValue,
                    difference.compareTo(BigDecimal.valueOf(0.01)) <= 0
            );

//            Assert.assertEquals("Expected: " + expectedVerifyCellValue + ", but was: " + actualCellValue, expectedVerifyCellValue, actualCellValue);
        } else {
            Assert.fail("Column not assigned in iSetCostToInBillingItemsCostAllocationTableAndVerifyCurrentCost method");
        }
    }

    @And("^(?:I |)click empty cell '(.+)' in table (.+)$")
    public void clickEmptyCellInTable(String columnName, String tableName) {
        SearchUtils.findElementAtCurrentPage(tableName, CheckableTable.class).checkEmptyCell(columnName);
    }

    @And("I delete all rows from {string} table")
    public void iDeleteAllRowsFromItemsTable(String table) throws InterruptedException {
        // Define your locators
        String deleteButtonXPath = "//*[@class='x-grid-item-container']//table[1]//*[@data-columnid='addRemoveDeleteColumn']//*[@data-qtip='Delete Row']";
        String gridVisibleXPath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]";

        // Loop until the grid element becomes visible
        while ($$x(gridVisibleXPath).filter(visible).isEmpty()) {
            if ($x(deleteButtonXPath).exists()) {
                $x(deleteButtonXPath).shouldBe(visible, enabled).click();
                sleep(2000); // Wait 2 seconds
            } else {
                System.out.println("Delete button not found, stopping loop.");
                break;
            }
        }

        // Confirm that the grid element is visible
        $x(gridVisibleXPath).shouldBe(visible);
        System.out.println("Grid element is now visible.");
    }
}
