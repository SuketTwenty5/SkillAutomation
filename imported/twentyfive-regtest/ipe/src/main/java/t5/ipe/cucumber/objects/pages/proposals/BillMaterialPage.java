package t5.ipe.cucumber.objects.pages.proposals;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.elements.CogMenu;
import t5.ipe.cucumber.objects.elements.IpeHumburgerTree;
import t5.ipe.cucumber.objects.elements.SimpleTree;
import t5.ipe.cucumber.objects.elements.tables.BillMaterialTable;
import t5.ipe.cucumber.objects.elements.tables.ClinsTable;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * CLINs page.
 */
@WebPage(value = "Bill Of Material page", urlTemplate = ".*quote:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Bill of Material")
public class BillMaterialPage extends MainPage {
    public static final String BillMaterial_TAB = "'Bill of Material' tab";

    @ElementName(BillMaterial_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='CLINs']")
    BaseWebElement billMaterialTab;

    @ElementName("'Bill of Material' table")
    @FindBy(xpath = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']")
    BillMaterialTable billMaterialTable;

    @ElementName("'Cog' menu")
    @FindBy(xpath = "//table[contains(@class, 'x-grid-item')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]")
    CogMenu cogMenu;

    @ElementName("'Yes' button")
    @FindBy(xpath = "//a//span[text()='Yes']")
    BaseWebElement yesButton;

    @ElementName("'Bill Of Material' tree")
    @FindBy(xpath = "//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]")
    SimpleTree billMaterialTree;

    @ElementName("'Run All Parts' button")
    @FindBy(xpath = "//span//span[text()='Run - All Parts']")
    BaseWebElement runAllPartsButton;

    @ElementName("'Hamburger' menu")
    @FindBy(xpath = "//a[@data-qtip='More']")
    IpeHumburgerTree hamburgerMenu;


    @Override
    public String getLoadTriggerName() {
        return BillMaterial_TAB;
    }


}


