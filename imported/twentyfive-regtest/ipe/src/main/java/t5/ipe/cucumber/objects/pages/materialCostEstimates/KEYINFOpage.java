package t5.ipe.cucumber.objects.pages.materialCostEstimates;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.IpeDropdownField;
import t5.ipe.cucumber.objects.elements.IpeField;
import t5.ipe.cucumber.objects.elements.IpeIdDropdownField;
import t5.ipe.cucumber.objects.pages.MainPage;
import t5.ipe.cucumber.objects.pages.masterData.keyInfoPage;

@WebPage(value = "Create Cost Source History KEY INFO page", urlTemplate = ".*#purchaseHistory:new$|", tabName = "KEY INFO")

public class KEYINFOpage extends keyInfoPage {

    @ElementName("'KEY INFO' tab")
    @FindBy(xpath = "//a[@aria-selected='true']//span[contains(text(),'KEY INFO') or contains(text(),'Key Info') or contains(text(),'Key info') or contains(text(),'key info')]")
    BaseWebElement keyInfoTab;

    @ElementName("'Items' tab")
    @FindBy(xpath = "//a//span[contains(text(),'Items')]")
    BaseWebElement itemsTab;

    @Override
    public String getLoadTriggerName() {
        return "'KEY INFO' tab";
    }
}
