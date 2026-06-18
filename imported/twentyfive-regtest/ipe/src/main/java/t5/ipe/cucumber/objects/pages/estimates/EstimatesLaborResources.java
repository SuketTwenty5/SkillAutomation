package t5.ipe.cucumber.objects.pages.estimates;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.LongWaitElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.LaborAmortizationTable;
import t5.ipe.cucumber.objects.elements.tables.LaborTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Estimates - Labor page.
 */
@WebPage(value = "Estimate Labor Resources", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Labor Resources")
public class EstimatesLaborResources extends EstimatesLabor {
    public static final String LABOR_TAB = "'LABOR' tab";
    private static final String RIGHT_HEADER_COLUMN_XPATH = "'Right' header";

    @ElementName(LABOR_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Labor' or text()='Engineering' or text()='Labor Resources']")
    LongWaitElement costStructureTab;

    @ElementName("'Labor Amortization' tab")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Labor Amortization']")
    LongWaitElement laborAmortizationTab;

    @ElementName(RIGHT_HEADER_COLUMN_XPATH)
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']")
    LongWaitElement rightHeaderXpath;

    @ElementName("'COPY LABOR' button")
    @FindBy(xpath = "//*[text()='COPY LABOR']/ancestor::*[@role='button' and @aria-hidden='false']")
    BaseWebElement copyLaborButton;


    @ElementName("'Total Effort' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//label[contains(text(),'Total effort')]/ancestor::td[@class='x-form-radio-group']//input")
    BaseWebElement totalEffortCheckBox;

    @ElementName("'Distribute Total Effort' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//label//*[contains(text(),'effort')]/ancestor::td//input[@aria-hidden='false']")
    IpeDropdownField distributeTotalEffortField;

    @ElementName("'Confirm' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']")
    BaseWebElement confirmButton;

    @ElementName("'Start of Amortization' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfAmortizationCheckBox;

    @ElementName("'End of Amortization' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfAmortizationCheckBox;

    @ElementName("'Start of Amortization' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfAmortizationDatePicker;

    @ElementName("'Start of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField startOfDepreciationDatePicker;

    @ElementName("'End of Amortization' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfAmortizationDatePicker;


    @ElementName("'End of Depreciation' datePicker")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input")
    IpeField endOfDepreciationDatePicker;

    @ElementName("'Start of Depreciation' checkBox")
    @FindBy(xpath = "//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span")
    IpeCheckBox startOfDepreciationCheckBox;

    @ElementName("'End of Depreciation' checkBox")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]")
    IpeCheckBox endOfDepreciationCheckBox;



    @ElementName("'Amortization Method' field")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]")
    IpeDropdownField amortizationMethodField;

    @ElementName("'Filter' button")
    @FindBy(xpath = "//*[@data-qtip='Filters' and @aria-hidden='false']")
    BaseWebElement filterButton;

    @ElementName("'Estimate for Labor' popup")
    @FindBy(xpath = "//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']")
    BaseWebElement estimateForLaborPopup;

    @ElementName("'Update Cost' link")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Update Cost')]")
    BaseWebElement updateCostLink;

    @ElementName("'Total Value' dropdown")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']")
    IpeDropdownField totalValueDropdown;

    @ElementName("'Description Gear' menuItems")
    @FindBy(xpath = "//*[@role='menu' and@aria-hidden='false' and @aria-expanded='true']")
    MenuItemField descriptionGearMenuItems;

    @ElementName("'Download' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Download' and @aria-hidden='false']")
    BaseWebElement downloadButton;

    @ElementName("'Upload' button")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Upload' and @aria-hidden='false']")
    BaseWebElement uploadButton;

    @ElementName("'Hamburger' menu")
    @FindBy(xpath = "//a[@data-qtip='More']")
    IpeHumburgerTree hamburgerMenu;

    @ElementName("'Data saved successfully' popUp")
    @FindBy(xpath = "//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    IpeNotification dataSavedSuccessfullyPopUp;

    @ElementName("'Labor Grid' rows")
    @FindBy(xpath = "//*[contains(@class,'x-grid-scroll-container')]//*[contains(@class,'x-grid-scrollbar-clipper')][2]//table[1]//tr//td[1]")
    BaseWebElement laborGridRows;

    @ElementName("'View' dropdown")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]")
    IpeViewDropdownField viewDropdown;

    @ElementName("'Learn more' link")
    @FindBy(xpath = "//a[@class='x-ibeLinksCls ' and text()='Learn more']")
    BaseWebElement learnMoreLink;

    @ElementName("'Click here to add' link")
    @FindBy(xpath = "//*[@class='x-grid-scrollbar-clipper ']//a/div")
    BaseWebElement addLink;

    @ElementName("'Edit Details' menuItem")
    @FindBy(xpath = "//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]")
    BaseWebElement editDetailsMenuItem;


    @ElementName("'Close' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']")
    BaseWebElement closeButton;

    @ElementName("'Labor' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborTable laborTable;

    @ElementName("'Amortization Schedule' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]")
    LaborAmortizationTable laborAmortizationTable;


    @ElementName("'Update Cost' button")
    @FindBy(xpath = "//*[@titlelink='updateCostsWithFormula']")
    BaseWebElement updCostButton;


    @ElementName("'Cog Settings' menu")
    @FindBy(xpath = "//div[(contains (@class, 'x-fit-item x-panel-default x-grid')) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]")
    IpeCogTree cogMenu;



    @ElementName("'Change Planning Mode' window")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-hidden='false']//div[contains(@class,'x-window-header')]//div[text()='Change Planning Mode in Other Estimates']")
    BaseWebElement changeWindow;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @Override
    public String getLoadTriggerName() {
        return RIGHT_HEADER_COLUMN_XPATH;
    }
}
