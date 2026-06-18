
package t5.ipe.cucumber.objects.elements.tables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Service table.
 * <p>
 * Created by: EKruze
 * Date: 17/01/2023
 */
public class ServiceTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//tr[contains(@class,'x-grid-row')]";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[contains(@class,'x-grid-row')])";
    private static final String XPATH_TEMPLATE = "//div[contains(@class, 'x-menu')][@aria-hidden='false']//a[@aria-hidden='false']//span[contains(text(),'%s')]";

    @Override
    public void
    setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        if (columnName.equals("Description")) {
            String nodeName = "Edit Description";
            scrollToViewAndHover(cellXpath);
            waitForCallOutVisible(cellXpath);
            clickCallOut(cellXpath);
            String nodeXPath = String.format(XPATH_TEMPLATE, nodeName);
            if ($x(nodeXPath).isDisplayed()) {
                $x(nodeXPath).hover().click();
            } else if (!$x(nodeXPath).isDisplayed()) {
                throw new RuntimeException(String.format("Not found item with name [%s]", nodeName));
            }
            enterValue(cellXpath, value);
            sendKeys(cellXpath + "//input", Keys.TAB, Keys.ENTER);
        } else if (columnName.equals("WBS") || columnName.equals("Resource Type") || columnName.equals("Work Package") || columnName.equals("Supplier Code") || columnName.equals("Supplier") || columnName.equals("Phase") || columnName.equals("Cost Element")) {
            String[] arr = value.split(" ", 2);
            String firstPart = arr[0];
            String secondPart = arr[1];
            scrollIntoViewAndClick(cellXpath + "//div");
            sendKeys(cellXpath + "//div//input", Keys.ARROW_DOWN);
            waitForElementNotVisible("//*[text()='Loading...']");
            scrollIntoViewAndClick("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']");
            waitForElementNotVisible("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]");
            sendKeys(cellXpath + "//input[not(@readonly)]", Keys.ENTER);
            setDefaultTimeout();

        } else if (columnName.equals("Start") || columnName.equals("End") || columnName.equals("Inflation from") || columnName.equals("Effort") || columnName.equals("Base Rate/Hr") || columnName.equals("Sequence") || columnName.equals("Solution Component") || columnName.equals("Price Unit") || columnName.equals("Source Date") || columnName.equals("Source Unit Cost") || columnName.equals("Source Qty") || columnName.matches(".*[0-9]{2}$") || columnName.equals("Start of Depreciation/Amortization") || columnName.equals("End of Depreciation/Amortization")) {

            scrollIntoViewAndClick(cellXpath);
            sendKeys(cellXpath + "//input[not(@readonly)]", value + Keys.ENTER);
            saveScreenshot();

        } else {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x("//div[@data-ref='listWrap']//ul[@aria-hidden='false']//li[contains(text(), '" + value + "')]").scrollIntoView(true).click();
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf(toSelenideElement().getText().equals(value)));

        }
    }

    private void scrollToViewAndHover(String xpath) {
        $x(xpath).scrollIntoView(true).hover();
    }

    private void waitForCallOutVisible(String xpath) {
        $x(xpath + "//div[contains(@class, 'ibeCallOut')]").shouldBe(visible);
    }

    private void clickCallOut(String xpath) {
        $x(xpath + "//div[contains(@class, 'ibeCallOut')]").click();
    }

    private void clickNode(String xpath) {
        $x(xpath).hover().click();
    }

    private void enterValue(String xpath, String value) {
        sendKeys(xpath + "//input", Keys.BACK_SPACE + value);
    }

    private void sendKeys(String xpath, CharSequence... keysToSend) {
        $x(xpath).sendKeys(keysToSend);
    }

    private void waitForElementNotVisible(String xpath) {
        $x(xpath).shouldNotBe(visible, Duration.ofSeconds(30));
    }

    private void scrollIntoViewAndClick(String xpath) {
        $x(xpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
    }

    private void saveScreenshot() {
        AllureUtils.saveScreenshot();
    }

    @Override
    public void
    deleteCellValue(String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
        $x(cellXpath + "//input[not(@readonly)]").shouldBe(visible, Duration.ofSeconds(15));
        $x(cellXpath + "//input[not(@readonly)]").clear();
        $x(cellXpath).sendKeys(Keys.ENTER);
    }


    @Override
    public int getColumnIndexByName(String columnName) {
        ElementsCollection headers = $$x(HEADER_COLUMN_XPATH);
        return findHeaderIndexByName(headers, columnName) - 1;
    }

    private int findHeaderIndexByName(ElementsCollection headers, String columnName) {
        return headers.stream()
                .filter(header -> header.getAttribute("outerText").equals(columnName))
                .mapToInt(headers::indexOf)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Header with name '" + columnName + "' not found on the page. Please check screenshot."));
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        int index = -1;
        ElementsCollection curWindowRows = getRows();
        int firstRowHash = 0;

        while (firstRowHash != curWindowRows.get(0).hashCode()) {
            firstRowHash = curWindowRows.get(0).hashCode();
            curWindowRows.get(0).scrollIntoView(true);
        }

        firstRowHash = 0;

        while (index == -1 && firstRowHash != curWindowRows.get(0).hashCode()) {
            try {
                firstRowHash = curWindowRows.get(0).hashCode();
                index = curWindowRows.indexOf($x(rowXPath));
            } catch (Throwable e) {
                curWindowRows.get(curWindowRows.size() - 1).scrollIntoView(true);
            }
        }

        return index;
    }

    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        int columnIndex = getColumnIndexByName(columnName);
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey();
            String cellValue = entry.getValue();
            int columnIndex = getColumnIndexByName(columnName);
            stringBuilder.append("[..//td[").append(columnIndex).append("]//*[contains(text(), '").append(cellValue).append("')]]");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        if (columnName.equals("Description")) {
            handleDescriptionColumn(columnName, rowFilter);
        } else if (columnName.equals("Sales Tax Formula") | columnName.equals("Maintenance Tax Formula")) {
            handlePencilEditColumn(columnName, rowFilter);
        } else if (columnName.equals("Subscription")) {
            String xpath = getCheckBoxElement(columnName, rowFilter);
            while (!$x(xpath + "[@class='x-grid-checkcolumn x-grid-checkcolumn-checked']").isDisplayed()) {
                $x(xpath).scrollIntoView(true).click();
            }
        } else {
            String xpath = getCheckBoxElement(columnName, rowFilter);
            $x(xpath).scrollIntoView(true).click();
        }
    }

    @Override
    public void uncheckCell(String columnName, Map<String, String> rowFilter) {
        if (columnName.equals("Description")) {
            handleDescriptionColumn(columnName, rowFilter);
        } else if (columnName.equals("Subscription")) {
            while ($x(getCheckBoxElement(columnName, rowFilter) + "[@class='x-grid-checkcolumn x-grid-checkcolumn-checked']").isDisplayed()) {
                $x(getCheckBoxElement(columnName, rowFilter)).scrollIntoView(true).click();
                AllureUtils.saveScreenshot();
                sleep(3000);
                AllureUtils.saveScreenshot();// Wait for a second before trying again
            }
            $x(getCheckBoxElement(columnName, rowFilter) + "[@class='x-grid-checkcolumn x-grid-checkcolumn-checked']").shouldNotBe(visible);
        } else {
            String xpath = getCheckBoxElement(columnName, rowFilter);
            $x(xpath).scrollIntoView(true).click();
        }
    }

    private void handleDescriptionColumn(String columnName, Map<String, String> rowFilter) {
        String optionName = "Edit Details";
        String cellXpath = getCellXpath(columnName, rowFilter);
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div[contains(@class, 'ibeCallOut')]").shouldBe(visible);
        $x(cellXpath + "//div[contains(@class, 'ibeCallOut')]").click();
// Choose "Edit Description" from the Cog menu
        String nodeXPath = String.format(XPATH_TEMPLATE, optionName);
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover().click();
            AllureUtils.saveScreenshot();
        } else {
            throw new RuntimeException(String.format("Not found item with name [%s]", optionName));
        }
    }

    private void handlePencilEditColumn(String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div[contains(@class, 'ibePencilEdit')]").shouldBe(visible);
        $x(cellXpath + "//div[contains(@class, 'ibePencilEdit')]").click();
    }

    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        StringBuilder resultBuilder = new StringBuilder();
        String xpath;
        switch (columnName) {
            case "Edit":
                xpath = "//img[@data-qtip='" + columnName + "']";
                break;
            case "Open":
                xpath = "//a[text()='" + columnName + "']";
                break;
            case "Subscription":
                xpath = "//span[@aria-label='Subscription']";
                break;
            case "+":
                xpath = "//div[@data-qtip='Add Row']";
                break;
            case "Copy Row":
                xpath = "//div[@data-qtip='Copy Row']";
                break;
            case "Delete Row":
                xpath = "//div[@data-qtip='Delete Row']";
                break;
            default:
                xpath = "//*[contains(text(), '" + columnName + "')]";
                break;
        }
        int rowIndex = getRowIndex(rowFilter) + 1;
        if (columnName.equals("+") || columnName.equals("Delete Row") || columnName.equals("Copy Row")) {
            resultBuilder.append("(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')])[").append(rowIndex).append("]").append(xpath);
        } else {
            resultBuilder.append("(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[contains(@class,'x-grid-row')])[").append(rowIndex).append("]").append(xpath);
        }

        return resultBuilder.toString();
    }

    @Override
    public String readCellValue(String columnName, Map<String, String> filter) {
        return $x(getCellXpath(columnName, filter)).text();
    }

    private ElementsCollection getRows() {
        return $$x(ALL_ROWS_XPATH);
    }

    private final static List<SelenideElement> rows = new ArrayList<>();

    @Override
    public int getRowsCount() {
        int prevHash = Integer.MIN_VALUE;
        int curWindowFirstRowHash = Integer.MAX_VALUE;
        Set<Integer> ids = new HashSet<>();
        while (prevHash != curWindowFirstRowHash) {
            ElementsCollection curWindowRows = getRows();
            prevHash = curWindowFirstRowHash;
            int test = curWindowRows.size() - 1;
            curWindowFirstRowHash = curWindowRows.get(0).hashCode();
            while (test > -1 && !ids.contains(curWindowRows.get(test).hashCode())) {
                SelenideElement row = curWindowRows.get(test);
                rows.add(row);
                ids.add(row.hashCode());
                test--;
            }
            curWindowRows.get(getRows().size() - 1).scrollIntoView(true);
        }
        return ids.size();
    }

}