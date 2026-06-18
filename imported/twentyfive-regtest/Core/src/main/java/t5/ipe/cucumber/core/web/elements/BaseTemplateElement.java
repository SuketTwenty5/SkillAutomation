package t5.ipe.cucumber.core.web.elements;

import com.codeborne.selenide.ElementsCollection;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.TemplateElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.annotation.Annotation;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;

/**
 * Class describes default functionality of TemplateElement interface.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public abstract class BaseTemplateElement extends BaseBlockElement implements TemplateElement {

    protected int index;
    protected String blockName;

    /**
     * @return locator of the element, which contains title of block
     */
    protected abstract String getBlockNameXpath();

    @Override
    public int getIndexByBlockName(String blockName) {
        this.blockName = blockName;
        ElementsCollection titleElements = $$x(getBlockNameXpath());
        List<String> blockTitles = titleElements.texts();
        int indexOf = blockTitles.indexOf(blockName);
        if (indexOf == -1) {
            throw new NoSuchElementException(
                    String.format("Block with name [%s] was not found.%nAvailable blocks are:%n[%s]",
                            blockName, blockTitles)
            );
        }
        return indexOf + 1;
    }

    @Override
    public TemplateElement focusOnElementByIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
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
                return String.format("((%s)[%d])%s", blockFindBy.xpath(), index, elementFindBy.xpath());
            }
        };
    }
}
