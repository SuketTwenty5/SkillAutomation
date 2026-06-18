package t5.ipe.cucumber.objects.pages.estimates;


import org.openqa.selenium.support.FindBy;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import t5.ipe.cucumber.core.web.interfaces.annotations.WebPage;
import t5.ipe.cucumber.objects.pages.MainPage;

/**
 * Estimates - Forecast page.
 */
@WebPage(value = "Estimate Forecast", urlTemplate = ".*#boe:[0-9A-Fa-f]{32}$|.*boe:[0-9A-Fa-f]{8}(?:-[0-9A-Fa-f]{4}){3}-[0-9A-Fa-f]{12}$", tabName = "Forecast")
public class EstimatesForecast extends MainPage {
    public static final String FORECAST_TAB = "'FORECAST' tab";

    @ElementName(FORECAST_TAB)
    @FindBy(xpath = "//a[@aria-selected='true']//span[text()='Forecast']")
    BaseWebElement forecastTab;

    @Override
    public String getLoadTriggerName() {
        return FORECAST_TAB;
    }
}
