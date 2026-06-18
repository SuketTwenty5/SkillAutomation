package t5.ipe.cucumber.objects.pages.masterData;

import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.MainPage;

@WebPage(value = "Item list page", urlTemplate = ".*#item$|")

public class itemListPage extends MainPage {
    public static final String ITEM_LISt = "'Item List' page";

    @ElementName(ITEM_LISt)
    @FindBy(xpath = "//div[contains(@class,'x-grid-item-container')]")
    BaseWebElement itemListContainer;


    @Override
    public String getLoadTriggerName() {
        return ITEM_LISt;
    }
}
