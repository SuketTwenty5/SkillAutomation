
package t5.ipe.cucumber.objects.elements.tables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

/**
 * Bids - Contract Lines - Cost Allocations table.
 * <p>
 * Created by: EKruze
 * Date: 08/02/2023
 */
public class ContractLinesCostAllocationsTable extends ContractLinesTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child x-panel-default'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child x-panel-default'))]//tr[contains(@class,'x-grid-row')]";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child x-panel-default'))]//table//tr[contains(@class,'x-grid-row')]//td";

    //    TODO refactor setCellValue
    @Override
    public void setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        if (columnName.equals("Sender WBS")) {
            String[] arr = value.split(" ", 2);
            String firstPart = arr[0];
            String secondPart = arr[1];
            $x(cellXpath + "//div").scrollIntoView(true).click();
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x("//*[text()='Loading...']").shouldNotBe(visible, Duration.ofSeconds(30));
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]").shouldNotBe(visible, Duration.ofSeconds(15));
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
        } else if (columnName.equals("Start") | columnName.equals("End") | columnName.equals("Effort") | columnName.equals("Base Rate/Hr") | columnName.equals("Sequence") | columnName.equals("Solution Component") | columnName.equals("Price Unit") | columnName.equals("% Cost") | columnName.equals("Amount") | columnName.equals("Percentage") | columnName.equals("Base Unit Cost") | columnName.equals("Source Qty") | columnName.equals("Qty - From") | columnName.equals("To Date") | columnName.matches(".*[0-9]{2}$")) {
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//input[not(@readonly)]").clear();
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value + Keys.ENTER);
        } else {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')]").scrollIntoView(true).click();
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf(toSelenideElement().getText().equals(value)));
        }

    }

    @Override
    public int getColumnIndexByName(String columnName) {
        ElementsCollection headers = $$x(HEADER_COLUMN_XPATH);
        SelenideElement header = headers
                .stream()
                .filter(x -> x.getAttribute("textContent").contains(columnName))
                .findFirst()
                .get();
        return headers.indexOf(header);
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        return $$x(ALL_ROWS_XPATH).indexOf($x(rowXPath).scrollIntoView(true));
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
            int columnIndex = getColumnIndexByName(columnName);
            stringBuilder.append("[..//td[").append(columnIndex + 1).append("]//*[contains(text(), '").append(cellValue).append("')]]");
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

    //    TODO refactor getCheckBoxElement
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
        if (columnName.equals("Cost Allocation")) {
            int columnIndex = getColumnIndexByName(columnName) + 1;
            xpath = "//td[" + columnIndex + "]";
        }
        int rowIndex = getRowIndex(rowFilter) + 1;
        return "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child x-panel-default'))]//table//tr[contains(@class,'x-grid-row')])[" + rowIndex + "]" + xpath;
    }

    @Override
    public String readCellValue(String columnName, Map<String, String> filter) {
        return $x(getCellXpath(columnName, filter)).text();
    }
}