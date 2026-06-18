package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Simple tree element interface.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Tree {

    /**
     * Performs search of first node from address, then expands it, and expands chain of inner nodes. Finally selects
     * @param nodeNames
     */
    void selectNode(String ... nodeNames);
}
