package t5.ipe.cucumber.objects.pages.masterData;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.IpeIdDropdownField;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "KEY INFO page", urlTemplate = ".*#item:new$|.*#item:new:type=HERS$|", tabName = "KEY INFO")


public class keyInfoPage  extends MainPage {

    public static final String KEY_INFO_TAB = "'KEY INFO' tab";

    @ElementName(KEY_INFO_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'KEY INFO') or contains(text(),'Key Info') or contains(text(),'Key info') or contains(text(),'key info')]")
    BaseWebElement keyInfoTab;

    @ElementName("'Part or Service Number' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Part or Service Number')]/ancestor::*[@rowspan='1'][1]//input")
    IpeField partOfServiceNumberField;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']/ancestor::*[@role='button' ]")
    BaseWebElement yesButton;

    @ElementName("'Description' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Description')]/ancestor::*[@rowspan='1'][1]//input")
    IpeField descriptionField;

    @ElementName("'Base Unit' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Base Unit')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField baseUnitField;

    @ElementName("'Type of Product or Service' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Type of Product or Service')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField typeOfProductOrServiceField;

    @ElementName("'Select Commodity Code or Material Group' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Select Commodity Code/Material Group')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField selectCommodityCodeOrMaterialGroupField;

    @ElementName("'Data saved successfully' dialog")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement dataSavedSuccessfullyDialog;

    @ElementName("'Planning & Estimating' tab")
    @FindBy(xpath = "//a[@role='tab']//span[contains(text(),'Planning & Estimating') or contains(text(),'PLANNING & ESTIMATING')]")
    BaseWebElement planningAndEstimatingTab;

    @ElementName("'Supplier Quote Type' field")
    @FindBy(xpath = "//input[@placeholder='Select Document Type' and @aria-hidden='false']")
    IpeDropdownField supplierQuoteTypeField;

    @ElementName("'Upload Files' button")
    @FindBy(xpath = "//*[text()='Upload Files']/ancestor::a[@aria-hidden='false']")
    BaseWebElement uploadFilesButton;

    @ElementName("'Supplier' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and text()='Supplier']/ancestor::*[@rowspan='1'][1]//input")
    IpeIdDropdownField supplierField;

    @ElementName("'Date' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and text()='Date']/ancestor::*[@rowspan='1'][1]//input")
    IpeField dateField;

    @ElementName("'Buyer' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and text()='Buyer']/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField buyerField;

    @ElementName("'Requester' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and text()='Requester']/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField requesterField;

    @ElementName("'Supplier Quote Valid From' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Valid From')]/ancestor::*[@rowspan='1'][1]//input")
    IpeField supplierQuoteValidFromField;

    @ElementName("'Supplier Quote Valid Until' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Valid Until')]/ancestor::*[@rowspan='1'][1]//input")
    IpeField supplierQuoteValidUntilField;

    @ElementName("'Organization' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and text()='Organization']/ancestor::*[@rowspan='1'][1]//input")
    IpeIdDropdownField organizationField;

    @ElementName("'Estimating Source' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Estimating Source')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField estimatingSourceField;

//    @ElementName("'Part or Service Number' field")
//    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Part or Service Number')]/ancestor::*[@rowspan='1'][1]//input")
//    IpeField partOrServiceNumberField;

    @Override
    public String getLoadTriggerName() {
        return KEY_INFO_TAB;
    }
}
