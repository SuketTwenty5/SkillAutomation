package t5.ipe.cucumber.core.web.elements;

import t5.ipe.cucumber.core.web.interfaces.element_interfaces.BlockElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Class describes default functionality of BlockElement interface.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public abstract class BaseBlockElement extends BaseWebElement implements BlockElement {
    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public Object performElementInitialization(Object element, String title, Field field) {
        if (!(element instanceof BaseWebElement)) {
            throw new ClassCastException("Page mapping error. Element " + title
                    + " is not an instance or inheritor of BaseWebElement.");
        }
        BaseWebElement baseWebElement = (BaseWebElement) element;
        FindBy elementFindBy = field.getAnnotation(FindBy.class);

        FindBy updatedFindBy = updateElementFindBy(this.getFindBy(), elementFindBy);
        baseWebElement.setFindBy(updatedFindBy);
        baseWebElement.setTitle(title);
        return baseWebElement;
    }

    protected FindBy updateElementFindBy(FindBy blockFindBy, FindBy elementFindBy) {
        return new FindBy() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return blockFindBy.annotationType();
            }

            @Override
            public How how() {
                return blockFindBy.how();
            }

            @Override
            public String using() {
                return blockFindBy.using();
            }

            @Override
            public String id() {
                return blockFindBy.id();
            }

            @Override
            public String name() {
                return blockFindBy.name();
            }

            @Override
            public String className() {
                return blockFindBy.className();
            }

            @Override
            public String css() {
                return blockFindBy.css();
            }

            @Override
            public String tagName() {
                return blockFindBy.tagName();
            }

            @Override
            public String linkText() {
                return blockFindBy.linkText();
            }

            @Override
            public String partialLinkText() {
                return blockFindBy.partialLinkText();
            }

            @Override
            public String xpath() {
                return String.format("%s%s", blockFindBy.xpath(), elementFindBy.xpath());
            }
        };
    }
}
