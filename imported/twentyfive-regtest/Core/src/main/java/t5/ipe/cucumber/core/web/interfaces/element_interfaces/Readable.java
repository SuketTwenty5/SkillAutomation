package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Interface implements properties of any UI element, which has text.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Readable {
    /**
     * Get text from element;
     * @return element text;
     */
    String readText();
}
