package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Interface implements property of UI element visibility.
 * Created by: EKruze
 * Date: 23/11/2022
 */
public interface Visible {

    /**
     * @return {@code true} - if element is visible at the moment.
     */
    boolean isVisible();

    boolean isEnabled();

    boolean isDisappeared();

    /**
     * Waits for element to be displayed.
     */
    void waitUntilBecomesVisible();

    /**
     * Waits for element to be invisible.
     */
    void waitUntilBecomesInvisible();
}
