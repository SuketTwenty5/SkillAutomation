package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Interface implements a property of UI element that can be clicked on.
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Clickable {

    /**
     * Clicking on an element.
     */
    void click();

    void hover();

    void uploadFile(String filePath);
}
