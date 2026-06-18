package t5.ipe.cucumber.core.web.interfaces.core;

/**
 * Web-page interface.
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Page extends WithName {

    @Override
    default String getName() {
        return getPageName();
    }

    /**
     * @return cucumber-name of current page.
     */
    String getPageName();

    /**
     * @return the cucumber-name of element which visibility means that current page is loaded.
     */
    String getLoadTriggerName();

    /**
     * Method contains default logic to check if the page is ready to use.
     */
    void checkIfPageLoaded();
}
