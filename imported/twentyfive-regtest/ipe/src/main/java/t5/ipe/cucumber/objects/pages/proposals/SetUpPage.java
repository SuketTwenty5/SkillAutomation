package t5.ipe.cucumber.objects.pages.proposals;


import com.codeborne.selenide.Condition;
import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.*;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;
import t5.ipe.cucumber.core.web.util.AllureUtils;
import t5.ipe.cucumber.objects.elements.*;
import t5.ipe.cucumber.objects.elements.tables.EscalationInflationRatesTable;
import t5.ipe.cucumber.objects.pages.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setCustomTimeoutSec;
import static t5.ipe.cucumber.core.web.util.web.WebUtil.setDefaultTimeout;

/**
 * Setup page - Accessed through the "Proposals" tab by the "New" button, this page contains the essential fields for initiating proposal creation.
 */
@WebPage(value = "Setup page", urlTemplate = ".*#quote:new:type=PS_consulting$|.*#quote:new:type=PS_consulting_simple$|.*#quote:new:type=PS_consulting_simple$|.*#quote:new:type=CP_capproject$|.*#proposal:new:type=PS_consulting_simple$|.*#proposal:new:type=DI_service$|.*:new:.*|.*proposal:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$|.*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "setup")
public class SetUpPage extends MainPage {
    public static final String SETUP_TAB = "'Setup' tab";
    public static final String COST_STRUCTURE_TAB = "'Cost Structure' tab";

    @ElementName(COST_STRUCTURE_TAB)
    @FindBy(xpath = "//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]")
    BaseWebElement costStructureTab;

    @ElementName("'Project Type' field")
    @FindBy(xpath = "//input[contains(@id,'iBEBusObjType')]")
    IpeDropdownField projectTypeFieldInSetup;

    @ElementName(SETUP_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'Setup') or contains(text(),'Opportunity Details')]")
    LongWaitElement setupTab;

    @ElementName("'Escalation and Inflammation Rates' table")
    @FindBy(xpath = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//div[@class='x-grid-item-container'])[2]")
    EscalationInflationRatesTable escalationAndInflammationRatesTable;

    @ElementName("'Open Project in SAP' link")
    @FindBy(xpath = "//a[@class='x-ibeLinksCls ' and text()='Open Project in SAP']")
    BaseWebElement openProjectInSapLink;

    @ElementName("'Upload RFx Files' button")
    @FindBy(xpath = "//a[contains(@id,'iBEFileUploadButton')]")
    BaseWebElement uploadRfxFiles;

    @ElementName("'Project Goals or Remarks' textbox")
    @FindBy(xpath = "//iframe[@class='tox-edit-area__iframe']")
    BaseWebElement projGoalRemTextBox;

    @ElementName("'Error' dialog")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-closable')]")
    TextElement errorDialog;

    @ElementName("'Text edit' toolbox")
    @FindBy(xpath = "//*[@data-alloy-vertical-dir='toptobottom' and @style='display: block;']")
    BaseWebElement textEditToolbox;

    @ElementName("'Edit Rates' link")
    @FindBy(xpath = "//a[text()='Edit Rates']")
    BaseWebElement editRatesLink;

    @ElementName("'SAP Project' field")
    @FindBy(xpath = "//a[text()='Open Project in SAP']/ancestor::td[1]//input")
    IpeIdDropdownField sapProjectField;

    @ElementName("'Edit Role or Alt Cust' link")
    @FindBy(xpath = "//a[text()='Edit Role/Alt. Cust.']")
    BaseWebElement editRoleOrAltCustLink;

    @ElementName("'Maintain Proposal-specific Currency Exchange Rates' popup")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Exchange') and @data-ref='textEl']")
    BaseWebElement exchangePopup;

    @ElementName("'Your Company' field")
    @FindBy(xpath = "//*[@placeholder='Select Company']")
    IpeDropdownField yourCompanyField;

    //Project Details
    @ElementName("'Description' field")
    @FindBy(xpath = "//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']")
    IpeField projectDescriptionField;

    @ElementName("'Title or Brief Description' field")
    @FindBy(xpath = "//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']")
    IpeField titleOrBriefDescriptionField;

    @ElementName("'Proposal Type' field")
    @FindBy(xpath = "//input[contains(@id,'iBEBusObjType')]")
    IpeDropdownField projectTypeField;

    @ElementName("'Several' radio")
    @FindBy(xpath = "//label[text()='Several']")
    IpeRadioButton severalRadio;

    @ElementName("'Single phase' radio")
    @FindBy(xpath = "//label[text()='Single phase']")
    IpeRadioButton singlePhaseRadio;

    @ElementName("'Planned Start' field")
    @FindBy(xpath = "//input[@placeholder='Select Start']")
    IpeField selectStartField;

    @ElementName("'Project Start' field")
    @FindBy(xpath = "//input[@placeholder='Select Start']")
    IpeField projectStartField;

    @ElementName("'End' field")
    @FindBy(xpath = "//input[@placeholder='Select End']")
    IpeEndDateField selectEndField;

    @ElementName("'Project End' field")
    @FindBy(xpath = "//input[@placeholder='Select End']")
    IpeEndDateField projectEndField;

    //Organizational Data
    @ElementName("'Capability' field")
    @FindBy(xpath = "//input[@placeholder='Select Company']")
    IpeDropdownField capabilityField;

    @ElementName("'Company' field")
    @FindBy(xpath = "//input[@placeholder='Select Company']")
    IpeDropdownField companyField;

    @ElementName("'Capability' dropdown")
    @FindBy(xpath = "//input[@placeholder='Select Company']/parent::div/following-sibling::div")
    BaseWebElement leadingCapabilityDropdown;

    @ElementName("'Leading Company' dropdown")
    @FindBy(xpath = "//input[@placeholder='Select Company']/parent::div/following-sibling::div")
    BaseWebElement leadingCompanyDropdown;

    @ElementName("'Leading Company' field")
    @FindBy(xpath = "//input[@placeholder='Select Company']")
    IpeIdDropdownField leadingCompanyField;

    @ElementName("'Leading Site or Department' field")
    @FindBy(xpath = "//input[@placeholder='Select Department']")
    IpeIdDropdownField leadingSiteOrDepartmentField;

    @ElementName("'Proposal Authorization#' field")
    @FindBy(xpath = "//*[text()='Proposal Authorization#']/ancestor::td[1]//input")
    IpeField proposalAuthorizationField;

    @ElementName("'Leading Site' dropdown")
    @FindBy(xpath = "//input[@placeholder='Select Department']/parent::div/following-sibling::div")
    BaseWebElement leadingSiteDropdown;

    @ElementName("'Project Goals or Remarks' field")
    @FindBy(xpath = "//iframe[@title='Rich Text Area']")
    IPEIFrameElement projectGoalsOrRemarksField;

    @ElementName("'Select Tag to Add' drop down")
    @FindBy(xpath = "//*[@placeholder='Select Tag to Add' and @aria-hidden='false']/parent::div/following-sibling::div")
    BaseWebElement selectTagToAddDropDown;

    @ElementName("'Select Tag to Add' options")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]")
    BaseWebElement selectTagToAddOptions;

    @ElementName("'Model' option")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']//*[contains(text(),'Model')]")
    BaseWebElement modelOption;

    @ElementName("'REGTAG' option")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']//*[contains(text(),'REGTAG')]")
    BaseWebElement regTagOption;

    @ElementName("'Pricing' tab")
    @FindBy(xpath = "//a[@aria-hidden='false' and @role='tab']//span[text()='Pricing']")
    BaseWebElement pricingTab;

    @ElementName("'Select Tag to Add' field")
    @FindBy(xpath = "//*[@placeholder='Select Tag to Add' and @aria-hidden='false']")
    IpeDropdownField selectTagToAddField;

    @ElementName("'Delete' link")
    @FindBy(xpath = "//a[text()='Delete']")
    BaseWebElement deleteLink;

    @ElementName("'Proposal Number' in Proposal Header")
    @FindBy(xpath = "//*[@class='x-component x-PricingAppActivityTitleText x-box-item x-component-default'][last()][(contains(text(),'(T5-') or contains(text(),'(2026-')) and contains(text(),')')]")
    TextElement proposalNumberInProposalHeader;

    @ElementName("'Leading Site' menuItem")
    @FindBy(xpath = "//*[@data-ref='listWrap']/ul[@class='x-list-plain' and @aria-hidden='false']")
    MenuItemField leadingSiteMenuitem;

    @ElementName("'Profit Center' dropdown")
    @FindBy(xpath = "//tr//span[text()='Profit Center']//following::div[4]/parent::div/following-sibling::div")
    BaseWebElement profitCenterDropdown;

    @ElementName("'CRM Opportunity' dropdown")
    @FindBy(xpath = "//tr//span[text()='CRM Opportunity']//following::input[1]/parent::div/following-sibling::div")
    BaseWebElement crmFieldDropdown;

    @ElementName("'Client Customer sell-to' dropdown")
    @FindBy(xpath = "//input[@placeholder='Enter company  name to search or click arrow for list']/parent::div/following-sibling::div")
    BaseWebElement clientFieldDropdown;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes']")
    BaseWebElement yesButton;

    @ElementName("'Yes and Update Costs' button")
    @FindBy(xpath = "//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes and Update Costs']")
    BaseWebElement yesAndUpdateCostsButton;

    @ElementName("'Customer Currency' dropdown")
    @FindBy(xpath = "//*[@placeholder='Default=USD']/parent::div/following-sibling::div")
    BaseWebElement customerCurrencyDropdown;

    @ElementName("'Customer Currency' field")
    @FindBy(xpath = "//*[@placeholder='Default=USD']")
    IpeDropdownField customerCurrencyField;


    @ElementName("'Leading Site' field")
    @FindBy(xpath = "//input[@placeholder='Select Department']")
    IpeDropdownField leadingSiteField;

    @ElementName("'Leading Department' field")
    @FindBy(xpath = "//input[@placeholder='Select Department']")
    IpeDropdownField leadingDepartmentField;

    @ElementName("'Project Currency' field")
    @FindBy(xpath = "//input[@placeholder='Default=USD']")
    IpeDropdownField projectCurrencyField;

    @ElementName("'Profit Center' field")
    @FindBy(xpath = "//tr//span[text()='Profit Center']//following::div[4]")
    IpeDropButton profitCenterField;



    @ElementName("'Cost Center' field")
    @FindBy(xpath = "//tr//span[text()='Cost Center']//following::div[3]//input")
    IpeField costCenterField;

    @ElementName("'Leading Company Currency' field")
    @FindBy(xpath = "//span[text()='Leading Company Currency *']//following::div[3]//input")
    IpeField companyCurrencyField;

    @ElementName("'CRM Opportunity' field")
    @FindBy(xpath = "//tr//span[text()='CRM Opportunity']//following::input[1]")
    IpeDropdownField crmField;

    @ElementName("'Data saved successfully' popUp")
    @FindBy(xpath = "//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    LongWaitElement dataSavedSuccessfullyPopUp;

    @ElementName("'Rich Text Editor View Dropdown' button")
    @FindBy(xpath = "//button[@data-mce-name='view']//div")
    BaseWebElement richTextEditorViewDropdown;

    @ElementName("'Full Screen' option")
    @FindBy(xpath = "//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Fullscreen' and @role='menuitem']")
    BaseWebElement fullScreenOption;

    @ElementName("'Normal' option")
    @FindBy(xpath = "//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Normal' and @role='menuitem']")
    BaseWebElement normalOption;

    @ElementName("'BOE copy is happening and Reschedule' warning")
    @FindBy(xpath = "//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']")
    LongWaitElement boeCopyProjectRescheduleWarningPopUp;

    @ElementName("'Client Customer sell-to' field")
    @FindBy(xpath = "//input[@placeholder='Enter company  name to search or click arrow for list']")
    IpeIdDropdownField clientField;

    @ElementName("'Select a Template' drop down")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[@data-ref='triggerWrap' and contains(@id,'iBESearchComboBox')]//*[contains(@class,'x-form-arrow-trigger-default')]")
    BaseWebElement selectTemplateDropDown;

    @ElementName("'Select a Template' options")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]")
    BaseWebElement selectTemplateOptions;

    @ElementName("'Select a prior proposal' radio button")
    @FindBy(xpath = "//label[text()='Select a prior proposal' or text()='Select a prior project']/preceding-sibling::*//input")
    IpeRadioButton priorProposalRadioButton;

    @ElementName("'Model' field")
    @FindBy(xpath = "//*[text()='Model']/ancestor::td[1]//input")
    IpeDropdownField modelField;

    @ElementName("'REGTAG' field")
    @FindBy(xpath = "//*[@role='tabpanel' and @aria-hidden='false']//*[text()='REGTAG']/ancestor::td[1]//input[@aria-hidden='false']/parent::*")
    IpeDropdownField regTagField;


    @ElementName("'Select a prior proposal' dropdown")
    @FindBy(xpath = " //*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-field x-iBECombo') and contains(@id,'iBESearchComboBox') and not(contains(@style, 'display: none'))]//*[contains(@class,'x-form-arrow-trigger-default')]")
    BaseWebElement priorProposalDropdown;

    @ElementName("'Select a prior proposal' options")
    @FindBy(xpath = "//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]")
    BaseWebElement priorProposalOptions;
    
    //Re-use Prior Work to Start from a Better Place
    @ElementName("'Select a template' radio")
//    @FindBy(xpath = "//label[text()='Select a Template']")
    @FindBy(xpath = "//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'select a template')]")
    IpeRadioButton selectTemplateRadio;

    @ElementName("'Re schedule' radio")
    @FindBy(xpath = "//label[contains(text(), 'Re-schedule proposal')]")
    IpeRadioButton rescheduleRadio;

    @ElementName("'proposal elements to create or copy' radioButtons")
    @FindBy(xpath = "//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'select a template')]")
    BaseWebElement proposalElementsToCreateOrCopy;

    @ElementName("'Update Rates' button")
    @FindBy(xpath = "//a[@aria-hidden='false']//*[text()='Update Rates']/ancestor::a")
    BaseWebElement updateRatesButton;

    @ElementName("'Reset all Rates to 1' option")
    @FindBy(xpath = "//*[text()='Turn-off Escalation (Reset all Rates to 1)']/ancestor::a")
    BaseWebElement ratesTo1Option;

    @ElementName("'Save Confirmation' popUp")
    @FindBy(xpath = "//*[@role='dialog' and @aria-disabled='false']//*[text()='Save Confirmation']")
    LongWaitElement saveConfirmationPupUp;

    @ElementName("'Force Update' popUp")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-disabled='false']")
    LongWaitElement forceUpdatePopUp;

    @ElementName("'Force Update Yes' button")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-disabled='false']//*[text()='Yes']")
    BaseWebElement forceUpdateYesButton;

    @ElementName("'Confirm' button")
    @FindBy(xpath = "//span[text()='Confirm']/ancestor::a")
    BaseWebElement confirmButton;

    @ElementName("'Close' button")
    @FindBy(xpath = "//span[text()='Close']/ancestor::a")
    BaseWebElement closeButton;

//    @ElementName("'Yes' button")
//    @FindBy(xpath = "//span[text()='Yes']/ancestor::a")
//    BaseWebElement yesButton;

    @ElementName("'Select a prior project' radio")
    @FindBy(xpath = "//label[text()='Select a prior project' or text()='Select a prior proposal' or text()='Select a Prior Project']")
    IpeRadioButton priorProposalProject;

    @ElementName("'Reset dates to' radio")
    @FindBy(xpath = "//label[contains(text(),'Reset dates to')]")
    IpeRadioButton resetDatesToRadio;

    @ElementName("'Select a prior bid' radio")
    @FindBy(xpath = "//label[text()='Select a prior bid']")
    IpeRadioButton priorProposalRadio;

    @ElementName("'Search for Template from Library' field")
    @FindBy(xpath = "//tr//*[contains(text(),'Template') or contains(text(),'template')]/ancestor::td//input[@role='combobox']")
    IpeTemplateDropdownField templateFromLibraryField;

    @ElementName("'Search Box' field")
    @FindBy(xpath = "//input[@role='combobox' and @aria-hidden='false' and contains(@data-componentid, 'iBESearchComboBox')]")
    IpeTemplateDropdownField searchBoxField;

    @ElementName("'Search for historical projects to copy' field")
    @FindBy(xpath = "//tr//span[text()='Search for historical projects to copy']//following::input[1]")
    IpeTemplateDropdownField historicalProjectsFromLibraryField;

    @ElementName("'Reschedule Project' window")
    @FindBy(xpath = "//div[contains(@class, 'x-window')]//*[text()='Reschedule Project']")
    BaseWebElement rescheduleProjectWindow;

    @ElementName("'Keep Exact Dates' radioButton")
    @FindBy(xpath = "//label[text()='Keep exact dates']/parent::*//input[@aria-hidden='false']")
    BaseWebElement keepExactDatesRadioButton;

    @ElementName("'Labor estimates' checkbox")
    @FindBy(xpath = "//label[contains(text(),'Labor estimates')]/ancestor::*[@class='x-form-radio-group']")
    IpeCheckBox laborEstimatesCheckbox;

    @ElementName("'Material estimates' checkbox")
    @FindBy(xpath = "//label[contains(text(),'Material estimates')]/ancestor::*[@class='x-form-radio-group']")
    IpeCheckBox materialEstimatesCheckbox;

    @ElementName("'Reschedule' button")
    @FindBy(xpath = "//a[@role='button' and @aria-hidden='false']//span[text()='Reschedule']")
    BaseWebElement rescheduleButton;

    @ElementName("'Escalation or Inflation Rates' tab")
    @FindBy(xpath = "//span[contains(text(),'Escalation or Inflation Rates')]")
    BaseWebElement escalationOrInflationRates;

    @ElementName("'Maintain Proposal-specific Currency Exchange Rates' popup")
    @FindBy(xpath = "//*[@aria-hidden='false' and @role='dialog']//*[text()='Maintain Proposal-specific Currency Exchange Rates']")
    BaseWebElement currencyExchangeRatesPopup;

    @ElementName("'Escalation first' cell")
    @FindBy(xpath = "//*[@role='dialog']//*[@class='x-grid-checkcolumn']/ancestor::td[@role='gridcell']")
    BaseWebElement escalationFirstCell;


    @Override
    public String getLoadTriggerName() {
        return SETUP_TAB;
    }

    @Override
    public void checkIfPageLoaded() {
        setCustomTimeoutSec(15);

        if (isSpinnerDisplayed()) {
            handleVisibleSpinner();
        }

        setDefaultTimeout();
        super.checkIfPageLoaded();
    }

    private boolean isSpinnerDisplayed() {
        try {
            $x("//*[contains(text(),'Loading document')]").shouldBe(visible);
            return true;
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is not displayed");
            return false;
        }
    }

    private void handleVisibleSpinner() {
        try {
            $x("//*[contains(text(),'Loading document')]").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        } catch (AssertionError ae) {
            AllureUtils.logAction("Spinner is displayed");
            $x("//*[contains(text(),'Loading document')]").shouldNotBe(Condition.visible, Duration.ofSeconds(100));
        }
    }

}
