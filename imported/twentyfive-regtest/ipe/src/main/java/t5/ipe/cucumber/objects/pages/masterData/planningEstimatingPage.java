package t5.ipe.cucumber.objects.pages.masterData;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.elements.IpeCustomDropdownField;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Planning & Estimating page", urlTemplate = ".*#item:.*$", tabName = "PLANNING & ESTIMATING")

public class planningEstimatingPage extends MainPage {

    public static final String PLANNING_AND_ESTIMATING_TAB = "'Planning & Estimating' tab";

    @ElementName(PLANNING_AND_ESTIMATING_TAB)
    @FindBy(xpath = "//a[@role='tab' and @aria-selected='true']//span[contains(text(),'Planning & Estimating') or contains(text(),'PLANNING & ESTIMATING')]")
    BaseWebElement planningAndEstimatingTab;

    @ElementName("'How should the system estimate cost of this item, product and service' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'How should the system estimate cost of this item')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField howShouldTheSystemEstimateCostOfThisItemProductAndServiceField;

    @ElementName("'Which quantity rule should be used to help estimate price' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Which quantity rule should be used to help estimate price')]/ancestor::*[@rowspan='1'][1]//input")
    IpeCustomDropdownField whichQuantityRuleShouldBeUsedToHelpEstimatePriceField;

    @ElementName("'How are proposal BOM quantities consolidated for costing' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'How are proposal BOM quantities consolidated for costing')]/ancestor::*[@rowspan='1'][1]//input")
    IpeCustomDropdownField howAreProposalBOMQuantitiesConsolidatedForCostingField;

    @ElementName("'Procurement Lead time in days' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and (contains(text(),'Procurement Lead-time (in days)') or contains(text(),'Procurement Lead-time'))]/ancestor::*[@rowspan='1'][1]//input")
    IpeField procurementLeadTimeInDaysField;

    @ElementName("'Data saved successfully' dialog")
    @FindBy(xpath = "//*[@role='dialog' and @aria-hidden='false']")
    BaseWebElement dataSavedSuccessfullyDialog;

    @ElementName("'Receipt & Inspection Time' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Receipt & Inspection Time')]/ancestor::*[@rowspan='1'][1]//input")
    IpeField receiptAndInspectionTimeField;

    @ElementName("'Quantity Curve' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Quantity Curve')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField quantityCurveField;

    @ElementName("'Escalation Factor' field")
    @FindBy(xpath = "//*[@data-ref='labelTextEl' and contains(text(),'Escalation Factor')]/ancestor::*[@rowspan='1'][1]//input")
    IpeDropdownField escalationFactorField;





    @Override
    public String getLoadTriggerName() {
        return PLANNING_AND_ESTIMATING_TAB;
    }
}
