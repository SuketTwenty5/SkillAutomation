package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * This interface is successor of BlockElement. <br/>Class which implements this interface is a template which occurs in
 * groups of block elements with same structure but different contents.<p/>
 * This interface extends its super by functionality for searching required block by name or by its index.
 * <p/>
 * Created by: EKruze<br/>
 * Date: 20/10/2023
 */
public interface TemplateElement extends BlockElement {
    /**
     * Performs focus operation on required block by its name.
     *
     * @return sought block.
     */
    default TemplateElement focusOnElementByName(String blockName) {
        return focusOnElementByIndex(getIndexByBlockName(blockName));
    }

    /**
     * Performs focus operation on required block by its index.
     *
     * @param index of elements block.
     * @return sought block.
     */
    TemplateElement focusOnElementByIndex(int index);

    /**
     * Obtain index of block by its name.
     *
     * @param blockName name of elements block.
     * @return index of elements block.
     */
    int getIndexByBlockName(String blockName);
}
