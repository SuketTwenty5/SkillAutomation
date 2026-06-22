package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
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
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$x;

public class BillingItemsTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String ALL_ROWS_XPATH = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//table[contains(@class, 'x-grid-item')]//tr[contains(@class,'x-grid-row') and not(contains(@class,'summary'))]";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')])";
    private static final String EDIT_XPATH = "//img[@data-qtip='Edit']";
    private static final String OPEN_XPATH = "//a[text()='Open']";
    private static final String CREATE_XPATH = "//a[text()='Create']";
    private static final String ADD_ROW_XPATH = "//div[@data-qtip='Add Row']";
    private static final String WP_XPATH = "//*[@aria-label='WP']";

    private ElementsCollection getRows() {
        return $$x(ALL_ROWS_XPATH);
    }

    @Override
    public int getRowsCount() {
        return getRows().size();
    }

    @Override
    public void setCellValue(String value, String columnName, Map<String, String> rowFilter) {
        String cellXpath = getCellXpath(columnName, rowFilter);
        if (columnName.equals("Phase") ) {
            setPhaseCellValue(cellXpath, value);
        } else if (columnName.equals("Start") | (columnName.equals("End"))) {
            setDateCellValue(cellXpath, value);
        } else if (columnName.equals("Maximum Quantity")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().contains(value)));
            AllureUtils.saveScreenshot();
        }  else if (columnName.equals("Price per Unit") || columnName.equals("Cost per Unit")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            SelenideElement input = $x(cellXpath + "//input[not(@readonly)]");
            executeJavaScript(
                    "arguments[0].value = arguments[1];",
                    input,
                    value
            );
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().contains(value)));
            AllureUtils.saveScreenshot();
        } else if (columnName.equals("Product or Service") || columnName.equals("Estimate")){
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
//            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            Selenide.sleep(700);
            $x("//div[@data-ref='listWrap']//ul//*[contains(text(), '" + value + "')]").click();
            Selenide.sleep(700);
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().equals(value)));
            AllureUtils.saveScreenshot();
        } else if ( columnName.equalsIgnoreCase("Estimating Strategy") ) {
            setCellValue(cellXpath, value);
        } else {
            setRegularCellValue(cellXpath, value);
        }
    }

    @Override
    public String readCellValue(String columnName, String rowIndex) {
        return $x(getCellXpath(columnName, rowIndex)).text();
    }

    @Override
    public String readCellValue(String columnName) {
        return $x(getCellXpath(columnName)).scrollIntoView(true).text();
    }

    private String getCellXpath(String columnName) {
        int columnIndex = getColumnIndexByName(columnName)+1;
        return CELL_XPATH + "[contains(@class,'x-grid-row-summary')]//td[" + columnIndex + "]";
    }

    private String getCellXpath(String columnName, String index) {
        int columnIndex = getColumnIndexByName(columnName) + 1;
        return CELL_XPATH + "[" + index + "]//td[" + columnIndex + "]";
    }

    private String getCellXpath(String columnName, int index) {
        int columnIndex = getColumnIndexByName(columnName) + 1;
        return CELL_XPATH + "[" + index + "]//td[" + columnIndex + "]";
    }

    @Override
    public void setCellValue(String value, String columnName, int index) {
        String cellXpath = getCellXpath(columnName, index);
        if (columnName.equals("Phase") ) {
            setPhaseCellValue(cellXpath, value);
        } else if (columnName.equals("Start") | (columnName.equals("End"))) {
            setDateCellValue(cellXpath, value);
        }else if (columnName.equals("Maximum Quantity")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().contains(value)));
            AllureUtils.saveScreenshot();
        } else if (columnName.equals("Price per Unit") || columnName.equals("Cost per Unit")) {
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            SelenideElement input = $x(cellXpath + "//input[not(@readonly)]");
            executeJavaScript(
                    "arguments[0].value = arguments[1];",
                    input,
                    value
            );
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().contains(value)));
            AllureUtils.saveScreenshot();
        } else if ( columnName.equalsIgnoreCase("Estimating Strategy") ) {
            setCellValue(cellXpath, value);
        } else if (columnName.equals("Product or Service") || columnName.equals("Estimate")){
            $x(cellXpath).scrollIntoView(true).hover();
            $x(cellXpath + "//div").scrollIntoView(true).click();
//            $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
            Selenide.sleep(900);
            $x(cellXpath + "//input[not(@readonly)]").clear();
            Selenide.sleep(700);
            if($x(cellXpath + "//div").isDisplayed()) {
                $x(cellXpath + "//div").click();
            }
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            Selenide.sleep(700);
            $x("//div[@data-ref='listWrap']//ul//*[contains(text(), '" + value + "')]").click();
            Selenide.sleep(700);
            $x(cellXpath + "//input").sendKeys(Keys.ENTER);
            $(String.valueOf($x(cellXpath + "//div").getText().equals(value)));
            AllureUtils.saveScreenshot();
        } else {
            setRegularCellValue(cellXpath, value);
        }
    }


    private void setDateCellValue(String cellXpath, String value) {
        $x(cellXpath).scrollIntoView(true).click();
        $x(cellXpath + "//input").sendKeys(value + Keys.ENTER);
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

    private void setCellValue(String cellXpath, String value) {
        Allure.step(String.format("Enter into Method setCellValue adding cellXpath %s with value %s", cellXpath, value));
        $x(cellXpath).shouldBe(visible, Duration.ofSeconds(5));
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div").scrollIntoView(true).click();
        $x(cellXpath + "//div//input").sendKeys(value + Keys.ARROW_DOWN + Keys.TAB);
        AllureUtils.saveScreenshot();
    }

    private void setRegularCellValue(String cellXpath, String value) {
        Allure.step(String.format("Enter into Method setRegularCellValue adding cellXpath %s with value %s", cellXpath, value));
        $x(cellXpath).scrollIntoView(true).click();
        $x(cellXpath + "//input").sendKeys(value + Keys.TAB + Keys.ENTER);
        AllureUtils.saveScreenshot();
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

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        System.out.println("Row xpath" + rowXPath);
        ElementsCollection rows = $$x(ALL_ROWS_XPATH);
        SelenideElement row = $x(rowXPath);
        int index = -2;
        if(row.exists()) {
            index = indexOf(rows, row);
        }
        if (index == -1) {
            throw new NoSuchElementException("Row not found for the given filter.");
        }
        if (index == -2){
            AllureUtils.logActionF("Row Does Not Exist");
        }

        return index;
    }


//    public int getRowIndexWarning(Map<String, String> filter) {
//        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
//        ElementsCollection rows = $$x(ALL_ROWS_XPATH);
//        SelenideElement row = $x(rowXPath);
//        int index = -1;
//        if(row.exists()) {
//            index = indexOf(rows, row);
//        }
//        if (index == -1) {
//            AllureUtils.logActionF("Row not found for the given filter.");
//        }
//        return index;
//    }

    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        //the first column is empty
        int columnIndex = getColumnIndexByName(columnName) + 1;
        int rowIndex = getRowIndex(rowFilter) + 1;
        return CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
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
            } else if ("Pricing Condition Dates".equals(columnName) || "Costing Condition Dates".equals(columnName)) {
                String datesXpath1 = DateDetector.buildContainsTextCondition(cellValue.split("-")[0].trim());
                String datesXpath2 = DateDetector.buildContainsTextCondition(cellValue.split("-")[1].trim());
                stringBuilder.append("[..//td[").append(columnIndex+1).append("]//*[(").append(datesXpath1).append(") and (").append(datesXpath2).append(")]]");
            } else if ("WBS Code".equals(columnName)) {
                stringBuilder.append("[..//td[")
                        .append(columnIndex + 1)
                        .append("]//*[text()='")
                        .append(cellValue)
                        .append("']]");
            }
            else {
                stringBuilder.append("[..//td[").append(columnIndex + 1).append("]//*[contains(text(), '").append(cellValue).append("')]]");
            }
        }
        AllureUtils.logActionF("Filter Xpath Created:", stringBuilder.toString());
        return stringBuilder.toString();
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    @Override
    public boolean isChecked(String columnName, Map<String, String> rowFilter) {
        Allure.step(String.format("Enter into Method isChecked columnName %s rowFilter %s", columnName, rowFilter));
        String xpath = getCheckBoxElement(columnName, rowFilter);
        SelenideElement checkbox = $x(xpath);
        String classAttr = checkbox.shouldBe(visible, Duration.ofSeconds(30)).getAttribute("class");
        return classAttr != null && classAttr.contains("x-grid-checkcolumn-checked");
    }

    @Override
    public void checkCell(String columnName, Map<String, String> rowFilter) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        Set<String> originalHandles = driver.getWindowHandles();
        System.out.println("Enter into Method checkCell WBS TABLE columnName %s rowFilter");
        String xpath = getCheckBoxElement(columnName, rowFilter);
        if(columnName.equals("Open")){
            System.out.println("🔍 XPath to be clicked: " + xpath);
            // Get the element
            SelenideElement el = $x(xpath).scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(30));
            // Print element details
            System.out.println("🖱️ Element text: " + el.getText());
            System.out.println("🖱️ Element outer HTML: " + el.getAttribute("outerHTML"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            el.hover();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            executeJavaScript("arguments[0].click();", el);
            System.out.println("✅ Click executed via JavaScript");
            System.out.println("URL After click" + WebDriverRunner.url());

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
            // Wait for new tab to appear
//            try {
//                new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
//                    Set<String> newHandles = d.getWindowHandles();
//                    System.out.println("🔍 Checking window handles... Current: " + newHandles.size() + ", Original: " + originalHandles.size());
//                    return newHandles.size() > originalHandles.size();
//                });
//                System.out.println("🆕 New tab detected!");
//            } catch (Exception timeoutException) {
//                System.out.println("❌ Timeout waiting for new tab to open.");
//                throw timeoutException;
//            }
        }
        else {
            $x(xpath).scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(30)).click();
        }
    }

    @Override
    public void checkEmptyCell(String columnName) {
        String xpath = getCheckBoxElement(columnName);
        ElementsCollection cells = $$x(xpath);
        SelenideElement emptyCell = getFirstEmptyCell(cells);
        emptyCell.scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(30)).click();
    }

    public SelenideElement getFirstEmptyCell(ElementsCollection cells) {
        return cells.stream()
                .filter(cell -> cell.getText().replace("\u00A0", "").trim().isEmpty())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No empty cell found"));
    }

    private String getCheckBoxElement(String columnName) {
        String xpath = null;
        switch (columnName) {
            case "Costing Condition Dates":
                xpath ="//*[@data-columnid='pricingConditionDates']";
                break;
            case "Costing Coins":
                xpath ="//*[contains(@class,'fa-coins')]";
                break;
            case "Maximum Quantity":
                xpath ="//*[contains(@class,'iBEQuantityColumn')]";
                break;
            case "Escalate to Commitment":
                xpath = "//*[@aria-label='Escalate to Commitment']";
                break;
            case "Part Description":
                xpath = "//*[@data-columnid='productDescColumn']";
                break;
            case "Product or Service":
            case "Part Number*":
                xpath = "//*[@data-columnid='productColumn']";
                break;
            case "Edit":
                xpath = EDIT_XPATH;
                break;
            case "Description":
                xpath = "//*[@data-columnid='quoteItemsGridDescriptionColumn']//div[text()]";
                break;
            case "WP":
                xpath = WP_XPATH;
                break;
            case "Open":
                xpath = OPEN_XPATH;
                break;
            case "Create":
                xpath = CREATE_XPATH;
                break;
            case "+":
                xpath = ADD_ROW_XPATH;
                break;
        }
        return "(//*[@role='tabpanel' and @aria-hidden='false']//table[contains(@class,'x-grid-item')]//tr[contains(@class,'x-grid-row') and @role='row'])" + xpath;
    }

    public void checkCell(String columnName, int index) {
        WebDriver driver = WebDriverRunner.getWebDriver();
        Set<String> originalHandles = driver.getWindowHandles();
        System.out.println("Enter into Method checkCell BillingItems TABLE columnName %s rowFilter");
        String xpath = getCheckBoxElement(columnName, index);
        if(columnName.equals("Open")){
            System.out.println("🔍 XPath to be clicked: " + xpath);
            // Get the element
            SelenideElement el = $x(xpath).scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(30));
            // Print element details
            System.out.println("🖱️ Element text: " + el.getText());
            System.out.println("🖱️ Element outer HTML: " + el.getAttribute("outerHTML"));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            el.hover();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            executeJavaScript("arguments[0].click();", el);
            System.out.println("✅ Click executed via JavaScript");
            System.out.println("URL After click" + WebDriverRunner.url());
        }
        else {
            $x(xpath).scrollIntoView(true).shouldBe(visible, Duration.ofSeconds(30)).click();
        }
    }


    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = null;
        switch (columnName) {
            case "Costing Condition Dates":
                xpath ="//*[@data-columnid='pricingConditionDates']";
                break;
            case "Costing Coins":
                xpath ="//*[contains(@class,'fa-coins')]";
                break;
            case "Maximum Quantity":
                xpath ="//*[contains(@class,'iBEQuantityColumn')]";
                break;
            case "Escalate to Commitment":
                xpath = "//*[@aria-label='Escalate to Commitment']";
                break;
            case "Edit":
                xpath = EDIT_XPATH;
                break;
            case "Description":
                xpath = "//*[@data-columnid='quoteItemsGridDescriptionColumn']//div[text()]";
                break;
            case "WP":
                xpath = WP_XPATH;
                break;
            case "Open":
                xpath = OPEN_XPATH;
                break;
            case "Create":
                xpath = CREATE_XPATH;
                break;
            case "+":
                xpath = ADD_ROW_XPATH;
                break;
        }

        int rowIndex = getRowIndex(rowFilter) + 1;
        return "(//*[@role='tabpanel' and @aria-hidden='false']//table[contains(@class,'x-grid-item')]//tr[contains(@class,'x-grid-row') and @role='row'])[" + rowIndex + "]" + xpath;
    }

    private String getCheckBoxElement(String columnName, int index) {
        String xpath = null;
        switch (columnName) {
            case "Costing Condition Dates":
                xpath ="//*[@data-columnid='pricingConditionDates']";
                break;
            case "Costing Coins":
                xpath ="//*[contains(@class,'fa-coins')]";
                break;
            case "Maximum Quantity":
                xpath ="//*[contains(@class,'iBEQuantityColumn')]";
                break;
            case "Escalate to Commitment":
                xpath = "//*[@aria-label='Escalate to Commitment']";
                break;
            case "Edit":
                xpath = EDIT_XPATH;
                break;
            case "Description":
                xpath = "//*[@data-columnid='quoteItemsGridDescriptionColumn']//div[text()]";
                break;
            case "WP":
                xpath = WP_XPATH;
                break;
            case "Open":
                xpath = OPEN_XPATH;
                break;
            case "Create":
                xpath = CREATE_XPATH;
                break;
            case "+":
                xpath = ADD_ROW_XPATH;
                break;
        }
        return "(//*[@role='tabpanel' and @aria-hidden='false']//table[contains(@class,'x-grid-item')]//tr[contains(@class,'x-grid-row') and @role='row'])[" + index + "]" + xpath;
    }


    @Override
    public String readCellValue(String columnName, Map<String, String> filter) {
        return $x(getCellXpath(columnName, filter)).text();
    }
}
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
// Wait for new tab to appear
//            try {
//                new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> {
//                    Set<String> newHandles = d.getWindowHandles();
//                    System.out.println("🔍 Checking window handles... Current: " + newHandles.size() + ", Original: " + originalHandles.size());
//                    return newHandles.size() > originalHandles.size();
//                });
//                System.out.println("🆕 New tab detected!");
//            } catch (Exception timeoutException) {
//                System.out.println("❌ Timeout waiting for new tab to open.");
//                throw timeoutException;
//            }
