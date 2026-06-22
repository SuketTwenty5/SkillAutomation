package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.DateDetector;
import t5.ipe.cucumber.objects.NumberStringDetector;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

public class LaborTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH
                = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//*[contains(@class,'x-grid-inner-normal') and @role='grid' and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']";
    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//tr[@role='row']";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[@role='row'])";
    private static final String XPATH_TEMPLATE = "//div[contains(@class, 'x-menu')][@aria-hidden='false']//a[@aria-hidden='false']//span[contains(text(),'%s')]";

    @Override
    public void setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        if (columnName.equals("Description")) {
            setDescription(value, cellXpath);
        } else if (isColumnWithCodeAndName(columnName)) {
            handleCustomColumnWithCodeAndName(value, columnName, cellXpath);
        } else if (isNoDropdownColumn(columnName)) {
            handleCustomColumn(value, columnName, cellXpath);
        } else {
            handleDefaultColumn(value, columnName, cellXpath);
        }
    }

    @Override
    public void setCellValue(String value, String columnName, int index) {
        String cellXpath = getCellXpath(columnName, index);
        if (columnName.equals("Description")) {
            setDescription(value, cellXpath);
        } else if (isColumnWithCodeAndName(columnName)) {
            handleCustomColumnWithCodeAndName(value, columnName, cellXpath);
        } else if (isNoDropdownColumn(columnName)) {
            handleCustomColumn(value, columnName, cellXpath);
        } else {
            handleDefaultColumn(value, columnName, cellXpath);
        }
    }

    private void setDescription(String value, String cellXpath) {
        String nodeName = "Edit Description";
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div[contains(@class, 'ibeCallOut')]").shouldBe(visible).click();

        String nodeXPath = String.format(XPATH_TEMPLATE, nodeName);
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover().click();
        } else {
            throw new RuntimeException(String.format("Not found item with name [%s]", nodeName));
        }

        $x(cellXpath + "//input").sendKeys(Keys.BACK_SPACE + value);
        $x(cellXpath + "//input").sendKeys(Keys.TAB, Keys.ENTER);
    }

    private void handleCustomColumnWithCodeAndName(String value, String columnName, String cellXpath) {
        setCustomTimeoutSec(45);
        String[] arr = value.split(" ", 2);
        String firstPart = arr[0];
        String secondPart = arr[1];
        try {
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").shouldBe(visible, Duration.ofSeconds(15));
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").click();
            $x("//*[contains(text(), 'Loading')]").shouldNotBe(visible, Duration.ofSeconds(30));
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(visible, Duration.ofSeconds(25)).click();
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]").shouldNotBe(visible, Duration.ofSeconds(15));
            setDefaultTimeout();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
        } finally {
            setDefaultTimeout();
        }
    }

    private void handleCustomColumn(String value, String columnName, String cellXpath) {
        try {
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value + Keys.ENTER);
        } finally {
            $(String.valueOf(toSelenideElement().getText().equals(value)));
        }
    }

    private void handleDefaultColumn(String value, String columnName, String cellXpath) {
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div").scrollIntoView(true).click();
        $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
        $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
        try {
             $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')]").shouldBe(visible, Duration.ofSeconds(15)).scrollIntoView(true).click();
        } catch (NoSuchElementException e){
            try{
                $x("//*[text()='Reset Now']").shouldBe(visible, Duration.ofSeconds(30)).scrollIntoView(true).click();
            } catch (NoSuchElementException ex){
                executeJavaScript(
                        "document.evaluate(\"//*[text()='Reset Now']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();"
                );
            }
            $x(cellXpath + "//div").shouldBe(visible, Duration.ofSeconds(15)).scrollIntoView(true).click();
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')]").shouldBe(visible, Duration.ofSeconds(15)).scrollIntoView(true).click();
        }
//        $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')]").scrollIntoView(true).click();
        try {
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
        } catch (NoSuchElementException e){
            AllureUtils.logActionF("Unable to press ENTER key after selecting value from dropdown");
        }
        $(String.valueOf(toSelenideElement().getText().equals(value)));
    }

    private boolean isNoDropdownColumn(String columnName) {
        return columnName.equals("Start") || columnName.equals("End") || columnName.equals("Inflation From") ||
                columnName.equals("Planned Effort") || columnName.equals("Effort") || columnName.equals("Sequence") || columnName.equals("Price Unit") ||
                columnName.equals("FTE") || columnName.equals("Base Unit Cost") || columnName.equals("Source Unit Cost") || columnName.equals("Source Date") ||
                columnName.matches(".*[0-9]{2}$");
    }

    private boolean isColumnWithCodeAndName(String columnName) {
        return columnName.equals("WBS") || columnName.equals("Capability")  || columnName.equals("Company") || columnName.equals("Delivery Organization") ||
                columnName.equals("Product or Service") || columnName.equals("Cost Element") ||
                columnName.equals("Phase") || columnName.equals("Delivery fr. Country") || columnName.equals("Sender WBS") || columnName.equals("Dept") ;
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
        return findHeaderIndexByName(headers, columnName);
    }

//    private int findHeaderIndexByName(ElementsCollection headers, String columnName) {
//        return headers.stream()
//                .filter(header -> {
//                    SelenideElement span = header.$x(".//span[@class='x-column-header-text-inner']");
//                    AllureUtils.logActionF(span.scrollIntoView(true).getText());
//                    return span.exists() && columnName.equals(span.scrollIntoView(true).getText());
//                })
//                .mapToInt(header -> indexOf(headers, header))
//                .findFirst()
//                .orElseThrow(() -> new NoSuchElementException("Header with name '" + columnName + "' not found on the page. Please check screenshot."));
//    }

    private int findHeaderIndexByName(ElementsCollection headers, String columnName) {
        if (headers == null || headers.isEmpty()) {
            AllureUtils.logActionF("Header list is null or empty");
            return -1;
        }

        try {
            for (int i = 0; i < headers.size(); i++) {
                try {
                    // Re-fetch the element to avoid stale references
                    SelenideElement span = $x(HEADER_COLUMN_XPATH+ "[" + (i+1) + "]//span[@class='x-column-header-text-inner']");

                    if (!span.exists()) continue;

                    String text = $x(HEADER_COLUMN_XPATH+ "[" + (i+1) + "]//span[@class='x-column-header-text-inner']").scrollIntoView(true)
                            .shouldBe(visible, Duration.ofMillis(3000))
                            .getText();
                    AllureUtils.logActionF("Matching header '%s' at index %d", text, i);
                    if (columnName.equals(text)) {
                        AllureUtils.logActionF("Matched header '%s' at index %d", text, i);
                        return i;
                    }

                } catch (Throwable innerEx) {
                    AllureUtils.logActionF("Header index %d check failed: %s", i, innerEx.toString());
                }
            }
            AllureUtils.logActionF("Header with name '%s' not found on the page. Please check screenshot.", columnName);
            return -1;
        } catch (Throwable outerEx) {
            AllureUtils.logActionF("Failed to find header index: %s", outerEx.toString());
            return -1;
        }
    }


    @Override
    public void checkCell(String columnName, int index) {
        if (columnName.equals("Description")) {
            handleDescriptionColumn(index);
        } else {
            handleDefaultColumn(columnName, index);
        }
    }

    private void handleDefaultColumn(String columnName, int index) {
        String xpath = getCheckBoxElement(columnName, index);
        $x(xpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").hover().click();
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        SelenideElement element = $x(rowXPath);
//        AllureUtils.logActionF("xpath of our row : ", element.toString());
        if (!element.exists()) {
            AllureUtils.logActionF("xpath not found. xpath:", rowXPath);
            return -1;
        }
//        AllureUtils.logActionF("xpath of all rows : ", ALL_ROWS_XPATH);
//        element.scrollIntoView(true);
        return indexOf($$x(ALL_ROWS_XPATH), element.scrollIntoView(true));
    }


    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        int columnIndex = getColumnIndexByName(columnName)+1;
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
    }

    private String getCellXpath(String columnName, int index) {
        int columnIndex = getColumnIndexByName(columnName)+1;
//        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + index + "]//td[" + columnIndex + "]";
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey() != null ? entry.getKey() : "";
            String cellValue = entry.getValue() != null ? entry.getValue() : "";
            int columnIndex = getColumnIndexByName(columnName)+1;
//            AllureUtils.logActionF("-----------------------------");
//            AllureUtils.logActionF("Column Name:" , columnName);
//            AllureUtils.logActionF("Column Index:" , columnIndex);
//            AllureUtils.logActionF("Cell Value:" , cellValue);
//            AllureUtils.logActionF("-----------------------------");
            if(DateDetector.isDate(cellValue)){
                String datesXpath = DateDetector.buildContainsTextCondition(cellValue);
                stringBuilder.append("[.//td[").append(columnIndex).append("]//*[").append(datesXpath).append("]]");
            }else if (NumberStringDetector.detectNumberType(cellValue).equals("Integer")) {
                String numberXpath = NumberStringDetector.buildXpathContainsBothIntegerDouble(cellValue);
                stringBuilder.append("[.//td[").append(columnIndex).append("]//*[").append(numberXpath).append("]]");
            }else {
                stringBuilder.append("[.//td[").append(columnIndex).append("]//*[contains(text(), '").append(cellValue).append("')]]");
            }
        }
        AllureUtils.logActionF("Appended Xpath:",stringBuilder.toString());

        return stringBuilder.toString();
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        if (columnName.equals("Description")) {
            handleDescriptionColumn(rowFilter);
        } else {
            handleDefaultColumn(columnName, rowFilter);
        }
    }

    @Override
    public void hoverCellAndClickGearMenu(String columnName, Map<String, String> rowFilter) {
        String xpath = getCellXpath(columnName, rowFilter);
        SelenideElement baseElement = $x(xpath)
                .scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(Condition.visible, Duration.ofSeconds(10))
                .hover();

        SelenideElement callOut = baseElement.$x("..//*[@class='ibeCallOut']");
        callOut.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    private void handleDescriptionColumn(int index) {
        String optionName = "Edit Details";
        String cellXpath = getCellXpath("Description", index);
        $x(cellXpath).scrollIntoView(true).hover();
        String callOutXpath = cellXpath + "//div[contains(@class, 'ibeCallOut')]";
        $x(callOutXpath).shouldBe(visible).click();

        String nodeXPath = String.format(XPATH_TEMPLATE, optionName);
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover().click();
        } else {
            throw new RuntimeException(String.format("Not found item with name [%s]", optionName));
        }
    }

    private void handleDescriptionColumn(Map<String, String> rowFilter) {
        String optionName = "Edit Details";
        String cellXpath = getCellXpath("Description", rowFilter);
        $x(cellXpath).scrollIntoView(true).hover();
        String callOutXpath = cellXpath + "//div[contains(@class, 'ibeCallOut')]";
        $x(callOutXpath).shouldBe(visible).click();

        String nodeXPath = String.format(XPATH_TEMPLATE, optionName);
        if ($x(nodeXPath).isDisplayed()) {
            $x(nodeXPath).hover().click();
        } else {
            throw new RuntimeException(String.format("Not found item with name [%s]", optionName));
        }
    }

    private void handleDefaultColumn(String columnName, Map<String, String> rowFilter) {
        String xpath = getCheckBoxElement(columnName, rowFilter);
        $x(xpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").hover().click();
    }

    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = generateXpath(columnName);
        int rowIndex = getRowIndex(rowFilter) + 1;
        return String.format(
                "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[contains(@class,'x-grid-scrollbar-clipper-locked')]//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                rowIndex, xpath
        );
    }

    private String getCheckBoxElement(String columnName, int index) {
        String xpath = generateXpath(columnName);
        int rowIndex = index;
        return String.format(
                "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[contains(@class,'x-grid-scrollbar-clipper-locked')]//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                rowIndex, xpath
        );
    }

    private String generateXpath(String columnName) {
        switch (columnName) {
            case "Expand":
                return "//*[contains(@aria-describedby,'description-collapsed')]//*[contains(@class,'x-tree-expander')]";
            case "Edit":
                return "//img[@data-qtip='" + columnName + "']";
            case "Open":
                return "//a[text()='" + columnName + "']";
            case "Subscription":
                return "//span[contains(@class, 'x-grid-checkcolumn')][@aria-label='Subscription']";
            case "Cost Allocation":
                return "//td[" + getColumnIndexByName(columnName) + 2 + "]";
            case "+":
                return "//div[@data-qtip='Add Row']";
            case "Copy Row":
            case "Delete Row":
                return "//div[@data-qtip='" + columnName + "']";
            default:
                return "//*[contains(text(), '" + columnName + "')]";
        }
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
        return getRows().size();
    }

}