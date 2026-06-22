
package t5.ipe.cucumber.objects.elements;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Workbench table.
 */
public class WorkbenchTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//tr[contains(@class,'x-grid-row')]";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')]//td";
    private static final String XPATH_TEMPLATE = "//div[contains(@class, 'x-menu')][@aria-hidden='false']//a[@aria-hidden='false']//span[contains(text(),'%s')]";

    private static final String ALL_HEADER_COLUMN_XPATH
            = "//div[contains(@class, 'x-column-header-align-right x-group-sub-header x-box-item')]";
    private static final String YEAR_HEADER_COLUMN_XPATH
            = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false' and contains(@class,'x-panel x-grid-inner-normal')]//*[@role='rowgroup' and @aria-hidden='false']//*[contains(@class,'x-column-header-default') and not(contains(@class,'x-group-sub-header'))]";

    private static final String MEASURES_HEADER_COLUMN_XPATH
            = "//div[contains(@class, 'x-column-header-align-right x-group-sub-header x-box-item')]";

    private static final String LOCKED_TABLE_HEADER_COLUMN_XPATH
            = "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-grid-inner-locked') and @aria-hidden='false']//*[@role='rowgroup' and @aria-hidden='false']//*[@role='columnheader' and @aria-hidden='false']";

    @Override
    public int getColumnIndexByName(String columnName) {
        if (columnName.equals("Bid Line Item > Cost or Revenue Component") || columnName.equals("Cost Type") || columnName.equals("Cost: Company Currency")) {
            return 0;
        } else if (columnName.equals("Description")) {
            return getLockedColumnIndex(columnName);
        } else if (columnName.contains("Grand total")) {
            String firstPart = "Grand total";
            int startIndex = columnName.indexOf(firstPart) + firstPart.length();
            String secondPart = columnName.substring(startIndex).trim();
            int yearInd = getColumnYearIndexByName(firstPart) + 1;
            int columnInd = getColumnNameIndexByNameWithoutMonth(firstPart, secondPart) + 1;
            String xpath = YEAR_HEADER_COLUMN_XPATH + "[" + yearInd + "]" + MEASURES_HEADER_COLUMN_XPATH + "[" + columnInd + "]";
            return indexOf($$x(ALL_HEADER_COLUMN_XPATH), $x(xpath).scrollIntoView(true));
        } else {
            String[] arr = columnName.split(" ", 2);
            AllureUtils.logActionF("Array of colum name"+ Arrays.toString(arr));
            if (arr.length==2) {
                String firstPart = arr[0];
                String secondPart = arr[1];
                int yearInd = getColumnYearIndexByName(firstPart) + 1;
                int columnInd = getColumnNameIndexByNameWithoutMonth(firstPart, secondPart) + 1;
                String xpath = YEAR_HEADER_COLUMN_XPATH + "[" + yearInd + "]" + MEASURES_HEADER_COLUMN_XPATH + "[" + columnInd + "]";
                int indexOnlyYear = indexOf($$x(ALL_HEADER_COLUMN_XPATH), $x(xpath).scrollIntoView(true));
                int indexBeforeYear = countStringsBeforeFirstNumber($$x(YEAR_HEADER_COLUMN_XPATH));
                AllureUtils.logActionF("Column '%s' index is %d + %d", columnName, indexOnlyYear, indexBeforeYear);
                if (arr[1].contains("Qtr:")) {
                    return indexOnlyYear + indexBeforeYear;
                }else{
                    return indexOnlyYear;
                }
            } else {
                String firstPart = arr[0];
                String xpath = YEAR_HEADER_COLUMN_XPATH + "//*[text()='"+firstPart+"']/ancestor::*[@role='columnheader' and @aria-hidden='false']";
                ElementsCollection headers = $$x(YEAR_HEADER_COLUMN_XPATH);
                // Find the header dynamically every time
                SelenideElement targetHeader = $x(xpath).shouldBe(visible, Duration.ofSeconds(7)).scrollIntoView(true);
                // Get index
                int index = -1;
                for (int i = 0; i < headers.size(); i++) {
                    if (headers.get(i).equals(targetHeader)) {
                        index = i;
                        break;
                    }
                }
                return index;
            }
        }
    }

    public static int countStringsBeforeFirstNumber(ElementsCollection elements) {
        int count = 0;

        for (SelenideElement element : elements) {
            String text = element.$x("./*[@data-ref='titleEl']").getText().trim();
            AllureUtils.logActionF("Text is before Year Column:-'%s'",text);
            if (text.matches("\\d+")) {   // stop when first number found
                break;
            } else {
                AllureUtils.logActionF("Counting header before year. %d Column '%s'", count, text);
                count++;
            }
        }

        return count;
    }

    public int getColumnYearIndexByName(String columnName) {
        ElementsCollection headers = $$x(YEAR_HEADER_COLUMN_XPATH);
        SelenideElement header = headers
                .stream()
                .filter(x -> x.$x("./*[@data-ref='titleEl']").getAttribute("textContent").contains(columnName))
                .findFirst()
                .get();
        return indexOf(headers, header);
    }

    public int getLockedColumnIndex(String columnName) {
        ElementsCollection headers = $$x(LOCKED_TABLE_HEADER_COLUMN_XPATH);
        SelenideElement header = headers
                .stream()
                .filter(x -> x.getAttribute("textContent").contains(columnName))
                .findFirst()
                .get();
        return indexOf(headers, header);
    }
    public int getColumnNameIndexByNameWithoutMonth(String firstValue, String secondValue) {
        int yearInd = getColumnYearIndexByName(firstValue) + 1;
        String xpath = YEAR_HEADER_COLUMN_XPATH + "[" + yearInd + "]" + MEASURES_HEADER_COLUMN_XPATH + "//span[@class='x-column-header-text-inner']";
        ElementsCollection headers = $$x(xpath);
        SelenideElement header = headers
                .stream()
                .filter(x -> x.getAttribute("textContent") != null && x.getAttribute("textContent").contains(secondValue))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No header found with text: " + secondValue));
        return indexOf(headers, header);
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        return indexOf($$x(ALL_ROWS_XPATH), $x(rowXPath).scrollIntoView(true));
    }

    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        int columnIndex = getColumnIndexByName(columnName) + 1;
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + columnIndex + "])[" + rowIndex + "]";
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey();
            String cellValue = entry.getValue();
            // Replace null with empty string
            if (cellValue == null) {
                cellValue = "";
            }
//            int columnIndex = (columnName.equals("Cost Type") || columnName.equals("Row labels")) ? getColumnIndexByName(columnName) : getColumnIndexByName(columnName) + 1;
            int columnIndex = getColumnIndexByName(columnName) + 1;
            stringBuilder.append("[//td[").append(columnIndex).append("]//*[contains(text(), '").append(cellValue).append("')]]");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        String xpath = getCheckBoxElement(columnName, rowFilter);
        $x(xpath).scrollIntoView(true).click();
    }


    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = null;
        if (columnName.equals("Edit")) {
            xpath = "//img[@data-qtip='" + columnName + "']";
        }
        if (columnName.equals("Open")) {
            xpath = "//a[text()='" + columnName + "']";
        }
        if (columnName.equals("+")) {
            xpath = "//div[@data-qtip='Add Row']";
        }
        if (columnName.equals("Delete Row")) {
            xpath = "//div[@data-qtip='Delete Row']";
        }
        int rowIndex = getRowIndex(rowFilter) + 1;
        return "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')])[" + rowIndex + "]" + xpath;
    }

    @Override
    public String readCellValue(String columnName, Map<String, String> filter) {
        return $x(getCellXpath(columnName, filter)).text();
    }
}