package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Allure;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.core.web.util.DataContainer;
import t5.ipe.cucumber.objects.DateDetector;

import java.time.Duration;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Phases table.
 * <p>
 * Created by: EKruze
 * Date: 14/11/2023
 */
public class PhasesTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//tr[contains(@class,'x-grid-row')]";
    private static final String HEADER_COLUMN_XPATH
            = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')])";

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        return indexOf($$x(ALL_ROWS_XPATH), $x(rowXPath));
    }

    @Override
    public int getColumnIndexByName(String columnName) {
        ElementsCollection headers = $$x(HEADER_COLUMN_XPATH);

        // Search for the desired column
        for (int i = 0; i < headers.size(); i++) {
            String headerText = headers.get(i).scrollIntoView(true).getText().trim();
            if (headerText.equalsIgnoreCase(columnName.trim())) {
                return i;
            }
        }

        AllureUtils.logActionF("Column with name %s is not displayed on the page. Perhaps the workbench table did not appear. Please check the screenshot.", columnName);
        throw new NoSuchElementException("Column with name " + columnName + " not found on the page.");
    }

    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        //the first column is empty
        int columnIndex = getColumnIndexByName(columnName) + 1;
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
    }

    @Override
    public void setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        if (columnName.equals("Phase") ) {
            setPhaseCellValue(cellXpath, value);
        } else if (columnName.equals("Start") | (columnName.equals("End"))) {
            setDateCellValue(cellXpath, value);
        }
        else if ( columnName.equalsIgnoreCase("Estimating Strategy") ) {
            setCellValue(cellXpath, value);
        } else {
            setRegularCellValue(cellXpath, value);
        }
    }

    private void setCellValue(String cellXpath, String value) {
        Allure.step(String.format("Enter into Method setCellValue adding cellXpath %s with value %s", cellXpath, value));
        $x(cellXpath).shouldBe(visible, Duration.ofSeconds(5));
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div").scrollIntoView(true).click();
        $x(cellXpath + "//div//input").sendKeys(value + Keys.ARROW_DOWN + Keys.TAB);
        AllureUtils.saveScreenshot();
    }

    private void setDateCellValue(String cellXpath, String value) {
        $x(cellXpath).scrollIntoView(true).click();
        $x(cellXpath + "//input").sendKeys(value + Keys.ENTER);
    }

    private void setRegularCellValue(String cellXpath, String value) {
        Allure.step(String.format("Enter into Method setRegularCellValue adding cellXpath %s with value %s", cellXpath, value));
        $x(cellXpath).scrollIntoView(true).click();
        $x(cellXpath + "//input").sendKeys(value + Keys.TAB + Keys.ENTER);
        AllureUtils.saveScreenshot();
    }

    private void setPhaseCellValue(String cellXpath, String value) {
        Allure.step(String.format("Enter into Method setPhaseCellValue adding cellXpath %s with value %s", cellXpath, value));
        $x(cellXpath).shouldBe(visible, Duration.ofSeconds(5));
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div").scrollIntoView(true).click();
        $x(cellXpath + "//div//input").clear();
        $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
        String listItemXpath = "//div[@data-ref='listWrap']//ul[@aria-hidden='false']//li[contains(text(), '" + value + "')]";
        $x(listItemXpath).shouldBe(visible, Duration.ofSeconds(5));
        $x(listItemXpath).click();
        $(String.valueOf(toSelenideElement().getText().equals(value)));
        AllureUtils.saveScreenshot();
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey();
            String cellValue = entry.getValue() != null ? DataContainer.resolveVariablesInText(entry.getValue()) : "";
//            String cellValue = DataContainer.resolveVariablesInText(entry.getValue());
            int columnIndex = getColumnIndexByName(columnName);
            if(Objects.equals(cellValue, "")){
                stringBuilder.append("[..//td[")
                        .append(columnIndex + 1)
                        .append("]//*[")
                        .append("not(contains(text(), '0')) and ")
                        .append("not(contains(text(), '1')) and ")
                        .append("not(contains(text(), '2')) and ")
                        .append("not(contains(text(), '3')) and ")
                        .append("not(contains(text(), '4')) and ")
                        .append("not(contains(text(), '5')) and ")
                        .append("not(contains(text(), '6')) and ")
                        .append("not(contains(text(), '7')) and ")
                        .append("not(contains(text(), '8')) and ")
                        .append("not(contains(text(), '9')) and ")
                        .append("not(contains(text(), 'a')) and ")
                        .append("not(contains(text(), 'b')) and ")
                        .append("not(contains(text(), 'c')) and ")
                        .append("not(contains(text(), 'd')) and ")
                        .append("not(contains(text(), 'e')) and ")
                        .append("not(contains(text(), 'f')) and ")
                        .append("not(contains(text(), 'g')) and ")
                        .append("not(contains(text(), 'h')) and ")
                        .append("not(contains(text(), 'i')) and ")
                        .append("not(contains(text(), 'j')) and ")
                        .append("not(contains(text(), 'k')) and ")
                        .append("not(contains(text(), 'l')) and ")
                        .append("not(contains(text(), 'm')) and ")
                        .append("not(contains(text(), 'n')) and ")
                        .append("not(contains(text(), 'o')) and ")
                        .append("not(contains(text(), 'p')) and ")
                        .append("not(contains(text(), 'q')) and ")
                        .append("not(contains(text(), 'r')) and ")
                        .append("not(contains(text(), 's')) and ")
                        .append("not(contains(text(), 't')) and ")
                        .append("not(contains(text(), 'u')) and ")
                        .append("not(contains(text(), 'v')) and ")
                        .append("not(contains(text(), 'w')) and ")
                        .append("not(contains(text(), 'x')) and ")
                        .append("not(contains(text(), 'y')) and ")
                        .append("not(contains(text(), 'z')) and ")
                        .append("not(contains(text(), 'A')) and ")
                        .append("not(contains(text(), 'B')) and ")
                        .append("not(contains(text(), 'C')) and ")
                        .append("not(contains(text(), 'D')) and ")
                        .append("not(contains(text(), 'E')) and ")
                        .append("not(contains(text(), 'F')) and ")
                        .append("not(contains(text(), 'G')) and ")
                        .append("not(contains(text(), 'H')) and ")
                        .append("not(contains(text(), 'I')) and ")
                        .append("not(contains(text(), 'J')) and ")
                        .append("not(contains(text(), 'K')) and ")
                        .append("not(contains(text(), 'L')) and ")
                        .append("not(contains(text(), 'M')) and ")
                        .append("not(contains(text(), 'N')) and ")
                        .append("not(contains(text(), 'O')) and ")
                        .append("not(contains(text(), 'P')) and ")
                        .append("not(contains(text(), 'Q')) and ")
                        .append("not(contains(text(), 'R')) and ")
                        .append("not(contains(text(), 'S')) and ")
                        .append("not(contains(text(), 'T')) and ")
                        .append("not(contains(text(), 'U')) and ")
                        .append("not(contains(text(), 'V')) and ")
                        .append("not(contains(text(), 'W')) and ")
                        .append("not(contains(text(), 'X')) and ")
                        .append("not(contains(text(), 'Y')) and ")
                        .append("not(contains(text(), 'Z'))")
                        .append("]]");
            } else if ("WBS Code".equals(columnName)) {
                stringBuilder.append("[..//td[")
                        .append(columnIndex + 1)
                        .append("]//*[text()='")
                        .append(cellValue)
                        .append("']]");

            } else if(DateDetector.isDate(cellValue)){
                columnIndex = columnIndex + 1;
                String datesXpath = DateDetector.buildContainsTextCondition(cellValue);
                stringBuilder.append("[.//td[").append(columnIndex).append("]//*[").append(datesXpath).append("]]");
            }
            else {
                stringBuilder.append("[..//td[").append(columnIndex + 1).append("]//*[contains(text(), '").append(cellValue).append("')]]");
            }
        }
        AllureUtils.logActionF("Filter Xpath Created:", stringBuilder.toString());
        return stringBuilder.toString();
    }

    private ElementsCollection getRows() {
        return $$x(ALL_ROWS_XPATH);
    }

    @Override
    public int getRowsCount() {
        return getRows().size();
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        String xpath = getCheckBoxElement(columnName, rowFilter);
        $x(xpath).click();
    }

    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = null;
        if (columnName.equals("Edit")) {
            xpath = "//img[@data-qtip='" + columnName + "']";
        }
        if (columnName.equals("Estimate")) {
            xpath = "//td[contains(@class,'quotePhaseBoeColumn')]//a";
        }
        if (columnName.equals("Open")) {
            xpath = "//a[text()='" + columnName + "']";
        }
        if (columnName.equals("+")) {
            xpath = "//div[@data-qtip='Add Row']";
        }
        if (columnName.equals("Option")) {
            xpath = "//span[contains(@class, 'x-grid-checkcolumn')][@aria-label='Option']";
        }
        int rowIndex = getRowIndex(rowFilter) + 1;
        return "(//table//tr[contains(@class,'x-grid-row') ])[" + rowIndex + "]" + xpath;
    }
}