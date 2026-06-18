package t5.ipe.cucumber.objects.elements.tables;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BillMaterialTable extends LaborTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[contains(@class, 'x-tabpanel-child') and not(contains(@class,'x-hidden-offsets'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')]//td";

    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//tr[contains(@class,'x-grid-row')]";

    @Override
    public int getColumnIndexByName(String columnName) {
        Selenide.sleep(5000);
        List<SelenideElement> headers = $$(By.xpath(HEADER_COLUMN_XPATH)).shouldHave(sizeGreaterThan(2), Duration.ofSeconds(20));
        return findHeaderIndexByName(headers, columnName);
    }

    private int findHeaderIndexByName(List<SelenideElement> headers, String columnName) {
        return headers.stream()
                .filter(header -> Objects.equals(header.getAttribute("outerText"), columnName))
                .mapToInt(headers::indexOf)
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Header with name '" + columnName + "' not found on the page. Please check screenshot."));
    }

    @Override
    public void setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        AllureUtils.logActionF("Enter setCellValue method with value %s columnName %s and rowFilter %s", value, columnName, rowFilter);
        String cellXpath = getCellXpath(columnName, rowFilter);
        AllureUtils.logActionF("cellXpath generated %s", cellXpath);
        $x(cellXpath).shouldBe(visible, Duration.ofMinutes(1));
       if (columnName.equals("Description")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div[@class='ibePencilEdit']").shouldBe(visible, Duration.ofSeconds(40));
            $x(cellXpath + "//div[@class='ibePencilEdit']").click();
            $x(cellXpath + "//input[not(@readonly)]").shouldBe(visible, Duration.ofSeconds(40)).sendKeys(value + Keys.ENTER);
            AllureUtils.saveScreenshot();
        } else if (columnName.equals("WBS") | columnName.equals("Domain or Location") | columnName.equals("Prod/Service")
                | columnName.equals("Receiver WBS")) {
            String[] arr = value.split(" ", 2);
            String firstPart = arr[0];
            String secondPart = arr[1];
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(visible, Duration.ofSeconds(40)).click();
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").shouldBe(visible, Duration.ofSeconds(40));
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").click();
            $x("//*[text()='Loading...']").shouldNotBe(visible, Duration.ofSeconds(60));
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]").shouldNotBe(visible, Duration.ofSeconds(30));
            $x(cellXpath + "//input[not(@readonly)]").shouldBe(visible, Duration.ofSeconds(40)).sendKeys(Keys.ENTER);
            AllureUtils.saveScreenshot();
        } else if (columnName.equals("Start") | columnName.equals("End") | columnName.equals("Hold Back %") | columnName.equals("%") | columnName.equals("Revenue Method") | columnName.equals("Effort") | columnName.equals("Cost Rate") | columnName.equals("Sequence") | columnName.equals("Solution Component") | columnName.equals("Date") | columnName.equals("Price Unit") | columnName.equals("Unit Price") | columnName.equals("Basic Rate") | columnName.equals("FTE") | columnName.equals("Title") | columnName.equals("Cost/Rev Impact - Most Likely") | columnName.equals("Milestone") | columnName.equals("Customer Payment (USD)") | columnName.equals("Contract Line") | columnName.matches(".*[0-9]{2}$")) {
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value + Keys.ENTER);
            AllureUtils.saveScreenshot();
        } else if (columnName.equals("From")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x("//div//*[contains(text(), '" + value + "')]").scrollIntoView(true).click();
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf(toSelenideElement().getText().equals(value)));
            AllureUtils.saveScreenshot();
        } else {
            AllureUtils.logActionF("Enter into Else method setCellValue");
            $x(cellXpath + "//div").shouldBe(visible, Duration.ofMinutes(1));
            $x(cellXpath).scrollIntoView(true).hover();
            executeJavaScript("arguments[0].click();", $x(cellXpath + "//div"));
            Selenide.sleep(2000);
            $x(cellXpath + "//div//input").sendKeys(Keys.CONTROL + "a");
            $x(cellXpath + "//div//input").sendKeys(Keys.BACK_SPACE);
            Selenide.sleep(2000);
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            Selenide.sleep(2000);
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            AllureUtils.saveScreenshot();
        }
    }

    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        int columnIndex = getColumnIndexByName(columnName) + 1;
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + columnIndex + "])[" + rowIndex + "]";
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        int index = -1;

        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        try {
            $x(rowXPath).shouldBe(visible, Duration.ofSeconds(50));
            index = $$x(ALL_ROWS_XPATH).indexOf($x(rowXPath).scrollIntoView(true));

        } catch (Throwable e) {
            throw new RuntimeException(String.format("Row not found by xpath, row does not exist [%s]", rowXPath));
        }

        return index;

    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey();
            String cellValue = entry.getValue();
            int columnIndex = getColumnIndexByName(columnName);
            stringBuilder.append("[..//td[").append(columnIndex + 1).append("]//*[contains(text(), '").append(cellValue).append("')]]");
        }
        return stringBuilder.toString();
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        AllureUtils.logActionF("Enter Method checkCell");
        String xpath = getCheckBoxElement(columnName, rowFilter);
        $x(xpath).shouldBe(visible, Duration.ofMinutes(1));
        AllureUtils.logActionF("Method checkCell xpath generated %s", xpath);
       // executeJavaScript("arguments[0].click();", $x(xpath));
        $x(xpath).click(ClickOptions.usingJavaScript());
      //  $x(xpath).scrollIntoView(true).hover().click();
    }

    //    TODO refactor getCheckBoxElement
    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = null;
        switch (columnName) {
            case "Edit":
                xpath = "//img[@data-qtip='" + columnName + "']";
                break;
            case "Open":
                xpath = "//a[text()='" + columnName + "']";
                break;
            case "Subscription":
                xpath = "//span[contains(@class, 'x-grid-checkcolumn')][@aria-label='Subscription']";
                break;
            case "Adjustment":
                xpath = "//span[contains(@class, 'x-grid-checkcolumn')][@aria-label='Adjustment']";
                break;
            case "+":
                xpath = "//div[@data-qtip='Add Row']";
                break;
            case "Delete Row":
                xpath = "//div[@data-qtip='Delete Row']";
                break;
            case "Cost Allocation":
                int columnIndex = getColumnIndexByName(columnName) + 1;
                xpath = "//td[" + columnIndex + "]";
                break;
            case "Description":
                xpath = "//*[@data-columnid='quoteItemsGridDescriptionColumn']";
                break;
            case "Copy Row":
                xpath = "//div[@data-qtip='Copy Row']";
                break;
            default:
                xpath = "//*[contains(text(), '" + columnName + "')]";
                break;
        }
        int rowIndex = getRowIndex(rowFilter) + 1;
        return "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')])[" + rowIndex + "]" + xpath;
    }
}