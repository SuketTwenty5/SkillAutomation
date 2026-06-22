package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Allure;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

public class CostBreakDownTable  extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String ALL_ROWS_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//tr[@role='row']";
    private static final String HEADER_COLUMN_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@aria-hidden='false' and @role='columnheader']";
    private static final String LEFT_HEADER_COLUMN_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-locked') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader' and not(contains(@clsss,'x-column-header-first '))]";
    private static final String RIGHT_HEADER_COLUMN_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader' and not(contains(@clsss,'x-column-header-first '))]";
    private static final String RIGHT_ROWS_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//tr[@role='row']";
    private static final String LEFT_ROWS_XPATH = "//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper x-grid-scrollbar-clipper-locked ']//tr[@role='row']";
    private static final String RIGHT_CELL_XPATH = "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[@role='row'])";
    private static final String LEFT_CELL_XPATH = "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper x-grid-scrollbar-clipper-locked ']//table//tr[@role='row'])";
    private static final String XPATH_TEMPLATE = "//*[@role='dialog' and @aria-hidden='false']//div[contains(@class, 'x-menu')][@aria-hidden='false']//a[@aria-hidden='false']//span[contains(text(),'%s')]";


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
    public String readCellValue(String columnName) {
        return $x(getHeaderCellXpath(columnName)).scrollIntoView(true).text();
    }

    private String getHeaderCellXpath(String columnName) {
        int columnIndex = getColumnIndexByName(columnName) + 1;
        return "//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-grid-inner-normal')]//*[contains(@class,'x-grid-row-summary')]//td[" + columnIndex + "]";
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
        boolean isSettingButtonVisible = $x(cellXpath + "//div[contains(@class, 'ibeCallOut')]").isDisplayed();
        if(isSettingButtonVisible){
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
        else {
            $x(cellXpath + "//div[contains(@class, 'x-grid-cell-inner')]").shouldBe(visible).click();
            $x(cellXpath + "//input").shouldBe(visible).sendKeys(Keys.BACK_SPACE + value);
            $x(cellXpath + "//input").shouldBe(visible).sendKeys(Keys.ENTER);
        }
    }

    private void handleCustomColumnWithCodeAndName(String value, String columnName, String cellXpath) {
        setCustomTimeoutSec(45);
        String[] arr = value.split(" ", 2);
        String firstPart = arr[0];
        String secondPart = arr[1];
        try {
            $x(cellXpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").shouldBe(visible, Duration.ofSeconds(15));
            boolean isMenuBoxVisible = $x("//*[@role='listbox' and @aria-hidden='false']").isDisplayed();
            if(!isMenuBoxVisible) {
                $x(cellXpath + "//div[contains(@class, 'x-form-trigger x-form-trigger-default')]").click();
            }
            $x("//*[contains(text(), 'Loading')]").shouldNotBe(visible, Duration.ofSeconds(30));
            Selenide.sleep(2000);
            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//div[text()='" + firstPart + "']//following-sibling::div[text()='" + secondPart + "']").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").shouldBe(visible, Duration.ofSeconds(25)).click();
            Selenide.sleep(2000);
//            $x("//ul[@data-ref='listEl' and @aria-hidden='false']//*[contains(text(), '" + value + "')]").shouldNotBe(visible, Duration.ofSeconds(15));
//            setDefaultTimeout();
            if (columnName.equals("Phase")){
                $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.TAB);
                $x("//td[@role='gridcell']//input").sendKeys(Keys.ENTER);
            }else {
                $x(cellXpath + "//input[not(@readonly)]").sendKeys(Keys.ENTER);
            }
        } finally {
            setDefaultTimeout();
            $(String.valueOf($x(cellXpath + "//div").getText().contains(secondPart)));
        }
    }

    private void handleCustomColumn(String value, String columnName, String cellXpath) {
        try {
            Selenide.sleep(1000);
            $x(cellXpath + "//div").scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").click();
            Selenide.sleep(3000);
            if($x(cellXpath + "//input[not(@readonly)]").isDisplayed()){
                AllureUtils.logActionF("Input is visible");
            } else {
                AllureUtils.logActionF("Input is not visible. Trying to click again");
                Selenide.executeJavaScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});" +
                                "arguments[0].click();",
                        $x(cellXpath + "//div")
                );
            }
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value + Keys.ENTER);
        } finally {
            $(String.valueOf($x(cellXpath + "//div").getText().equals(value)));
        }
    }

    private void handleDefaultColumn(String value, String columnName, String cellXpath) {
        $x(cellXpath).scrollIntoView(true).hover();
        $x(cellXpath + "//div").scrollIntoView(true).click();
        Selenide.sleep(1000);
        if ($x(cellXpath + "//div//input[@aria-hidden='false']").exists()){
            AllureUtils.logActionF("Input is visible");
        } else {
            AllureUtils.logActionF("Input is not visible. Trying to click again");
            Selenide.executeJavaScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});" +
                            "arguments[0].click();",
                    $x(cellXpath + "//div")
            );
        }
        $x(cellXpath + "//div//input").sendKeys(Keys.ARROW_DOWN);
        $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
        try {
            $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')] | " +
                    "//div[@data-ref='listWrap']//ul//li//*[contains(text(), '" + value + "')]"
            ).scrollIntoView(true).click();
        }catch(ElementNotFound e){
            AllureUtils.logActionF("Second Attempt to Enter data");
            $x(cellXpath + "//input[not(@readonly)]").clear();
            $x(cellXpath + "//input[not(@readonly)]").sendKeys(value);
            $x("//div[@data-ref='listWrap']//ul//li[contains(text(), '" + value + "')] | " +
                    "//div[@data-ref='listWrap']//ul//li//*[contains(text(), '" + value + "')]"
            ).scrollIntoView(true).click();
        }

        $x(cellXpath + "//input").sendKeys(Keys.ENTER);
        if(columnName.equals("Part Number")){
            Selenide.sleep(7000);
            AllureUtils.logActionF("Wait For Re-cost 'Yes' button Over");
            ElementsCollection yesButtons = $$x("//*[contains(@class,'x-title-text') and contains(text(),'Recost')]/ancestor::*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']");
            if (!yesButtons.isEmpty()) {
                SelenideElement yesButton = yesButtons.first();
                if (yesButton.isDisplayed()) {
                    yesButton.click();
                    AllureUtils.logActionF("✅ 'Yes' button in Recost dialog is visible and clicked.");
                } else {
                    AllureUtils.logActionF("⚠️ 'Yes' button is in DOM but not displayed.");
                }
            } else {
                AllureUtils.logActionF("⚠️ 'Yes' button in Recost dialog not present in DOM.");
            }
            Selenide.sleep(2000);
        }
        AllureUtils.saveScreenshot();
        if(!columnName.equals("Fringe Formula")) {
            $(String.valueOf($x(cellXpath + "//div").getText().equals(value)));
        }
    }

    private boolean isNoDropdownColumn(String columnName) {
        return columnName.equals("Start") || columnName.equals("End") || columnName.equals("Inflation From") || columnName.equals("Setup Hrs") ||
                columnName.equals("Planned Effort") || columnName.equals("Sequence") || columnName.equals("Price Unit") ||
                columnName.equals("FTE") || columnName.equals("Base Unit Cost") || columnName.equals("Unit Cost") || columnName.equals("Run Time") || columnName.equals("Quantity") || columnName.equals("Source Unit Cost") || columnName.equals("Source Date") ||
                columnName.matches(".*[0-9]{2}$");
    }

    private boolean isColumnWithCodeAndName(String columnName) {
        return columnName.equals("WBS") || columnName.equals("Capability") || columnName.equals("Delivery Organization") ||
                columnName.equals("Product or Service") || columnName.equals("Cost Element") ||
                columnName.equals("Phase") || columnName.equals("Delivery fr. Country") || columnName.equals("Sender WBS") || columnName.equals("Task") || columnName.equals("Service Type");
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

    public static String determineTableType(String columnName) {
        if(columnName.contains("Dropdown")){
            columnName = columnName.split(" ")[0];
        }
        String baseXpath =
                "//*[@role='columnheader' and @aria-hidden='false']" +
                        "//*[@class='x-column-header-text-inner' and " +
                        "contains(translate(normalize-space(text()), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), " +
                        "translate('%s', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))]" +
                        "/ancestor::*[@role='rowgroup' and @aria-hidden='false']" +
                        "/ancestor::*[contains(@class,'%s')]";
        String normalXpath = String.format(baseXpath, columnName, "x-panel x-grid-inner-normal");
        String lockedXpath = String.format(baseXpath, columnName, "x-panel x-grid-inner-locked");

        boolean normalExists = !$$x(normalXpath).filter(visible).isEmpty();
        boolean lockedExists = !$$x(lockedXpath).filter(visible).isEmpty();
        if(columnName.equals("+") || columnName.equals("Copy Row") || columnName.equals("-")){
            lockedExists=true;
        }
        if ((normalExists && lockedExists) || (!normalExists && !lockedExists)) {
            throw new RuntimeException("❌ Error: Either both or none of the table types exist for column: " + columnName);
        }

        String tableType = normalExists ? "normal" : "locked";
        System.out.println("✅ Table type for column '" + columnName + "': " + tableType);
        return tableType;
    }

    @Override
    public int getColumnIndexByName(String columnName) {
        String tableType =determineTableType(columnName);
        System.out.println("✅ Table type for column '" + columnName + "': " + tableType);
        String xpath;
        if(tableType.equals("normal")){
            xpath = RIGHT_HEADER_COLUMN_XPATH;
        } else {
            xpath = LEFT_HEADER_COLUMN_XPATH;
        }
        ElementsCollection headers = $$x(xpath);
        return findHeaderIndexByName(headers, columnName);
    }

    private int findHeaderIndexByName(ElementsCollection headers, String columnName) {
//        String allHeaders = IntStream.range(0, headers.size())
//                .mapToObj(i -> {
//                    SelenideElement span = headers.get(i).$x(".//span[@class='x-column-header-text-inner']");
//                    String text = span.exists() ? span.getText().trim() : "[Missing]";
//                    return String.format("[%d]=%s", i, text);
//                })
//                .collect(Collectors.joining(" | "));
//        AllureUtils.logActionF("Available headers while searching for columnName '%s': %s", columnName , allHeaders);

        if (headers == null || headers.isEmpty()) {
            AllureUtils.logActionF("Header list is null or empty");
            return -1;
        }

        try {
            return headers.stream()
                    .filter(header -> {
                        try {
                            SelenideElement span = header.$x(".//span[@class='x-column-header-text-inner']");
                            if (!span.exists()) return false;

                            String text = span.scrollIntoView(true)
                                    .shouldBe(visible, Duration.ofMillis(700))
                                    .getText();

                            AllureUtils.logActionF("Checking header text: {}", text);
                            return columnName.equalsIgnoreCase(text);
                        } catch (Throwable e) {
                            AllureUtils.logActionF("Header check failed: {}", e.toString());
                            return false;
                        }
                    })
                    .map(header -> {
                        try {
                            int index = indexOf(headers, header);
                            AllureUtils.logActionF("Matched header at index: {}", index);
                            return index;
                        } catch (Throwable e) {
                            AllureUtils.logActionF("Index fetch failed: {}", e.toString());
                            return -1;
                        }
                    })
                    .filter(index -> index >= 0)
                    .findFirst()
                    .orElse(-1);
        } catch (Throwable outer) {
            AllureUtils.logActionF("Failed to find header index: {}", outer.toString());
            return -1;
        }

    }

    public static void splitFilter(Map<String, String> filter,
                                   Map<String, String> normalFilter,
                                   Map<String, String> lockedFilter) {

        for (Map.Entry<String, String> entry : filter.entrySet()) {
            String columnName = entry.getKey();
            String value = entry.getValue();

            String tableType = determineTableType(columnName);
            if (tableType.equals("normal")) {
                normalFilter.put(columnName, value);
            } else {
                lockedFilter.put(columnName, value);
            }
        }
    }

    @Override
    public int getRowIndex(Map<String, String> filter) {
        Map<String, String> normalFilter = new HashMap<>();
        Map<String, String> lockedFilter = new HashMap<>();
        splitFilter(filter, normalFilter, lockedFilter);
        System.out.println("Normal Table Filter: " + normalFilter);
        System.out.println("Locked Table Filter: " + lockedFilter);
        String leftRowXPath = appendXPathByFilter(LEFT_ROWS_XPATH, lockedFilter);
        String rightRowXPath = appendXPathByFilter(RIGHT_ROWS_XPATH, normalFilter);
        SelenideElement elementLeft = $x(leftRowXPath);
        SelenideElement elementRight = $x(rightRowXPath);

//        AllureUtils.logActionF("xpath of our row : ", element.toString());
        if (!elementLeft.exists() && !elementRight.exists()) {
            AllureUtils.logActionF("xpath Element Left", leftRowXPath);
            AllureUtils.logActionF("xpath Element Right", rightRowXPath);
            return -1;
        }
        if (normalFilter.isEmpty() && lockedFilter.isEmpty()) {
            throw new RuntimeException("❌ Error: No Side Table Information found");
        }
        if (normalFilter.isEmpty()) {
            int rowIndex = -1;
            try {
                rowIndex = indexOf($$x(LEFT_ROWS_XPATH), elementLeft.scrollIntoView(true));
            } catch (com.codeborne.selenide.ex.ElementNotFound | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("❌ Element not found while trying to find index in LEFT_ROWS_XPATH: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("❌ Unexpected error while finding index in LEFT_ROWS_XPATH: " + e.getMessage());
                e.printStackTrace();
            }
            return rowIndex;
        } else if (lockedFilter.isEmpty()) {
            int rowIndex = -1;
            try {
                rowIndex = indexOf($$x(RIGHT_ROWS_XPATH), elementRight.scrollIntoView(true));
            } catch (com.codeborne.selenide.ex.ElementNotFound | org.openqa.selenium.NoSuchElementException e) {
                System.err.println("❌ Element not found while trying to find index in LEFT_ROWS_XPATH: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("❌ Unexpected error while finding index in LEFT_ROWS_XPATH: " + e.getMessage());
                e.printStackTrace();
            }
            return rowIndex;
        } else {
            int leftIndex = indexOf($$x(LEFT_ROWS_XPATH), elementLeft.scrollIntoView(true));
            int rightIndex = indexOf($$x(RIGHT_ROWS_XPATH), elementRight.scrollIntoView(true));
            if(leftIndex!=rightIndex){
                throw new RuntimeException("❌ Row element not found in either table");
            }
            else{
                return indexOf($$x(RIGHT_ROWS_XPATH), elementRight.scrollIntoView(true));
            }
        }
    }

//    @Override
//    public int getRowIndex(Map<String, String> filter) {
//        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
//        SelenideElement element = $x(rowXPath);
////        AllureUtils.logActionF("xpath of our row : ", element.toString());
//        if (!element.exists()) {
//            AllureUtils.logActionF("xpath not found. xpath:", rowXPath);
//            return -1;
//        }
////        AllureUtils.logActionF("xpath of all rows : ", ALL_ROWS_XPATH);
////        element.scrollIntoView(true);
//        return indexOf($$x(ALL_ROWS_XPATH), element.scrollIntoView(true));
//    }


    private String getCellXpath(String columnName, Map<String, String> rowFilter) {
        String tableType =determineTableType(columnName);
        System.out.println("✅ While getCellXpath Table type for column '" + columnName + "': " + tableType);
        int columnIndex = getColumnIndexByName(columnName)+1;
        int rowIndex = getRowIndex(rowFilter)+1;
        if(tableType.equals("normal")){
            return RIGHT_CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
        } else {
            return LEFT_CELL_XPATH + "[" + rowIndex + "]//td[" + columnIndex + "]";
        }
    }

    private String getCellXpath(String columnName, int index) {
        String tableType =determineTableType(columnName);
        System.out.println("✅ While getCellXpath Table type for column '" + columnName + "': " + tableType);
        int columnIndex = getColumnIndexByName(columnName)+1;
        if(tableType.equals("normal")){
            return RIGHT_CELL_XPATH + "[" + index + "]//td[" + columnIndex + "]";
        } else {
            return LEFT_CELL_XPATH + "[" + index + "]//td[" + columnIndex + "]";
        }
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey() != null ? entry.getKey() : "";
            String cellValue = entry.getValue() != null ? entry.getValue() : "";
            int columnIndex = getColumnIndexByName(columnName) + 1;
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
    public void checkCell(String columnName, int index) {
        if (columnName.equals("Description")) {
            handleDescriptionColumn(index);
        } else {
            handleDefaultColumn(columnName, index);
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

    @Override
    public boolean isChecked(String columnName, Map<String, String> rowFilter) {
        Allure.step(String.format("Enter into Method isChecked columnName %s rowFilter %s", columnName, rowFilter));
        String xpath = getCheckBoxElement(columnName, rowFilter);
        SelenideElement checkbox = $x(xpath);
        String classAttr = checkbox.shouldBe(visible, Duration.ofSeconds(30)).getAttribute("class");
        return classAttr != null && classAttr.contains("x-grid-checkcolumn-checked");
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


    private void handleDefaultColumn(String columnName, Map<String, String> rowFilter) {
        String xpath = getCheckBoxElement(columnName, rowFilter);
        try {
            $x(xpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").hover().click();
        } catch (UIAssertionError | ElementClickInterceptedException e){
            AllureUtils.logActionF("First attempt to click checkbox failed, retrying...");
            Selenide.executeJavaScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});" +
                            "arguments[0].click();",
                    $x(xpath)
            );
        }
    }

    private void handleDefaultColumn(String columnName, int index) {
        String xpath = getCheckBoxElement(columnName, index);
        try {
            $x(xpath).scrollIntoView("{behavior: \"instant\", block: \"end\", inline: \"nearest\"}").hover().click();
        } catch (UIAssertionError | ElementClickInterceptedException e){
            AllureUtils.logActionF("First attempt to click checkbox failed, retrying...");
            Selenide.executeJavaScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});" +
                            "arguments[0].click();",
                    $x(xpath)
            );
        }
    }

    private String getCheckBoxElement(String columnName, Map<String, String> rowFilter) {
        String xpath = generateXpath(columnName);
        int rowIndex = getRowIndex(rowFilter) + 1;
        String tableType = determineTableType(columnName);
        if(tableType.equals("normal")){
            return String.format(
                    "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                    rowIndex, xpath
            );
        } else {
            return String.format(
                    "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[contains(@class,'x-grid-scrollbar-clipper-locked')]//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                    rowIndex, xpath
            );
        }
    }

    private String getCheckBoxElement(String columnName, int rowIndex) {
        String xpath = generateXpath(columnName);
        String tableType = determineTableType(columnName);
        if(tableType.equals("normal")){
            return String.format(
                    "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-scrollbar-clipper ']//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                    rowIndex, xpath
            );
        } else {
            return String.format(
                    "(//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[contains(@class,'x-grid-scrollbar-clipper-locked')]//table//tr[contains(@class,'x-grid-row') and @role='row'])[%d]%s",
                    rowIndex, xpath
            );
        }
    }

    private String generateXpath(String columnName) {
        if(columnName.contains("Dropdown")){
            columnName = "Dropdown";
        }
        switch (columnName) {
            case "Edit":
                return "//img[@data-qtip='" + columnName + "']";
            case "Open":
                return "//a[text()='" + columnName + "']";
            case "Fringe Formula":
                return "//*[@class='ibePencilEdit']";
            case "Purchased":
                return "//*[@data-qtip='" + columnName + "' or @data-columnid='materialGridPurchasedColumn']//*[@role='button']";
            case "Subscription":
                return "//span[contains(@class, 'x-grid-checkcolumn')][@aria-label='Subscription']";
            case "Cost Allocation":
                return "//td[" + getColumnIndexByName(columnName) + 2 + "]";
            case "+":
                return "//div[@data-qtip='Add Row']";
            case "Copy Row":
            case "Delete Row":
                return "//div[@data-qtip='" + columnName + "']";
            case "Dropdown":
                return "//*[contains(@class,'x-form-arrow-trigger')]";
            case "WBS":
                return "//*[contains(@data-columnid,'GridWbsIDColumn') or contains(@data-columnid,'materialGridWBSColumn')]//div";
            case "Task":
                return "//*[contains(@data-columnid,'GridTaskIDColumn') or contains(@data-columnid,'GridTaskToAssignColumn')]//div";
            default:
                return "//*[contains(text(), '" + columnName + "')]";
        }
    }

    @Override
    public String readCellValue(String columnName, Map<String, String> filter) {
        return $x(getCellXpath(columnName, filter)).text();
    }

    private ElementsCollection getRows(String columnName) {
        String xpath;
        String tableType = determineTableType(columnName);
        if(tableType.equals("normal")){
            xpath = RIGHT_ROWS_XPATH;
        } else {
            xpath = LEFT_ROWS_XPATH;
        }
        return $$x(xpath);
    }

    private ElementsCollection getRows() {
        return $$x(ALL_ROWS_XPATH);
    }

    private final static List<SelenideElement> rows = new ArrayList<>();

    @Override
    public int getRowsCount(String columnName) {
        return getRows(columnName).size();
    }

    @Override
    public int getRowsCount() {
        return getRows().size();
    }
}
