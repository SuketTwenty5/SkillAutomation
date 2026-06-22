package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

public class LaborAmortizationTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH
            = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='rowgroup' and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']";
    private static final String ALL_ROWS_XPATH = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-grid-item-container']//tr";
    private static final String CELL_XPATH = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-grid-item-container']//table//tr";
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

    public static String convertToString(Object value) {
        if (value instanceof Integer) {
            return Integer.toString((Integer) value);
        } else if (value instanceof String) {
            return (String) value;
        } else {
            return "Unsupported type";
        }
    }

    @Override
    public void setCellValue(String value, String columnName, int index) {
        String cellXpath = getCellXpath(columnName, convertToString(index));
        if (columnName.equals("Description")) {
            setDescription(value, cellXpath);
        } else if (columnName.equals("Sender WBS")) {
            setSenderWBS(value, cellXpath);
        }else if (isColumnWithCodeAndName(columnName)) {
            handleCustomColumnWithCodeAndName(value, columnName, cellXpath);
        } else if (isNoDropdownColumn(columnName)) {
            handleCustomColumn(value, columnName, cellXpath);
        } else {
            handleDefaultColumn(value, columnName, cellXpath);
        }
    }

    private void setSenderWBS(String value, String cellXpath) {
        setCustomTimeoutSec(45);
        String[] arr = value.split(" ", 2);
        String firstPart = arr[0];
        String secondPart = arr[1];
        try {
            $x(cellXpath + "//div[@class='x-grid-cell-inner ']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            Selenide.sleep(7000);
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").shouldBe(visible, Duration.ofSeconds(15)).click();
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(visible, Duration.ofSeconds(25)).click();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
//            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").shouldBe(visible, Duration.ofSeconds(15)).click();
//            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(visible, Duration.ofSeconds(25)).click();
//            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]").shouldNotBe(visible, Duration.ofSeconds(15));
//            setDefaultTimeout();

        } finally {
            String finalActualValue = $x(cellXpath + "//div[@class='x-grid-cell-inner ']").shouldBe(visible, Duration.ofSeconds(15)).getText();
            Assert.assertEquals(value, finalActualValue);
            setDefaultTimeout();
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
        $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')]").scrollIntoView(true).click();
        $x(cellXpath + "//input").sendKeys(Keys.ENTER);
        $(String.valueOf(toSelenideElement().getText().equals(value)));
    }

    private boolean isNoDropdownColumn(String columnName) {
        return columnName.equals("Start") || columnName.equals("End") || columnName.equals("Inflation From") ||
                columnName.equals("Planned Effort") || columnName.equals("% Cost") ||columnName.equals("Sequence") || columnName.equals("Price Unit") ||
                columnName.equals("FTE") || columnName.equals("Base Unit Cost") || columnName.equals("Source Unit Cost") || columnName.equals("Source Date") ||
                columnName.matches(".*[0-9]{2}$");
    }

    private boolean isColumnWithCodeAndName(String columnName) {
        return columnName.equals("WBS") || columnName.equals("Capability") || columnName.equals("Delivery Organization") ||
                columnName.equals("Product or Service") || columnName.equals("Cost Element") ||
                columnName.equals("Phase") || columnName.equals("Delivery fr. Country");
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
    public void
    deleteCellValue(String columnName, int index) {
        String cellXpath = getCellXpath(columnName, String.valueOf(index));
        $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
        if(columnName.equals("% Cost")) {
            $x(cellXpath + "//input[not(@readonly)]").shouldBe(visible, Duration.ofSeconds(15));
            $x(cellXpath + "//input[not(@readonly)]").clear();
        } else{
            $x(cellXpath + "//input[not(@readonly)]").shouldBe(visible, Duration.ofSeconds(15));
            $x(cellXpath + "//input[not(@readonly)]").clear();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
        }
    }

    @Override
    public int getColumnIndexByName(String columnName) {
        ElementsCollection headers = $$x(HEADER_COLUMN_XPATH);
        return findHeaderIndexByName(headers, columnName)+1;
    }

    private int findHeaderIndexByName(ElementsCollection headers, String columnName) {
        return headers.stream()
                .filter(header -> {
                    SelenideElement span = header.$x(".//span[@class='x-column-header-text-inner']");
                    return span.exists() && columnName.equals(span.getText());
                })
                .mapToInt(header -> indexOf(headers, header))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Header with name '" + columnName + "' not found on the page. Please check screenshot."));
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
        int columnIndex = getColumnIndexByName(columnName);
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
    }

    private String getCellXpath(String columnName, String rowIndex) {
        int columnIndex = getColumnIndexByName(columnName);
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
    }

    private String getCellXpath(String columnName) {
        int columnIndex = getColumnIndexByName(columnName);
        return CELL_XPATH + "[contains(@class,'x-grid-row-summary')]//td[" + columnIndex + "]";
    }

    @Override
    public boolean isChecked(String columnName, Map<String, String> rowFilter) {
        Allure.step(String.format("Enter into Method isChecked columnName %s rowFilter %s", columnName, rowFilter));
        String xpath = getCheckBoxElement(columnName, rowFilter);
        SelenideElement checkbox = $x(xpath);
        String classAttr = checkbox.shouldBe(visible, Duration.ofSeconds(30)).getAttribute("class");
        return classAttr != null && classAttr.contains("x-grid-checkcolumn-checked");
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey() != null ? entry.getKey() : "";
            String cellValue = entry.getValue() != null ? entry.getValue() : "";
            int columnIndex = getColumnIndexByName(columnName);
//            AllureUtils.logActionF("-----------------------------");
//            AllureUtils.logActionF("Column Name:" , columnName);
//            AllureUtils.logActionF("Column Index:" , columnIndex);
//            AllureUtils.logActionF("Cell Value:" , cellValue);
//            AllureUtils.logActionF("-----------------------------");
            stringBuilder.append("[.//td[").append(columnIndex).append("]//*[contains(text(), '").append(cellValue).append("')]]");
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

    private String generateXpath(String columnName) {
        switch (columnName) {
            case "Use":
                return "//*[@role='button' and @aria-label='Use']";
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
    public String readCellValue(String columnName) {
        return $x(getCellXpath(columnName)).scrollIntoView(true).text();
    }

    @Override
    public String readCellValue(String columnName, String rowIndex) {
        return $x(getCellXpath(columnName, rowIndex)).text();
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
