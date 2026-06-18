package t5.ipe.cucumber.core.web.interfaces.core;

import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.annotations.ElementName;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface provides default logic for finding elements by name.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface ElementMap {

    /**
     * Method executes element search by its title value at mapping class. In case, when element with required type is
     * absent in the mapping class of current page or found element by name in mapping class is not implements required
     * interface, then exception will be thrown.
     *
     * @param title application element name which is used in cucumber steps.
     * @param requiredType expected element interface. In current architecture the same element could implement more
     *                     than one interface, thereafter, it is possible to interact with the same element through different
     *                     steps using different interfaces.
     * @return page-mapping element casted to required type.
     */
    default <T> T findElementByTitle(String title, Class<T> requiredType) {
        Field field = this.getElement(title);
        Object element = instantiateElementByType(field.getType());
        Object initializedElement = performElementInitialization(element, title, field);
        if (!requiredType.isAssignableFrom(initializedElement.getClass())) {
            throw new IllegalArgumentException(
                    String.format("Syntax error: [%1$s] does not implements interface [%2$s].%nPossible variants of fix:%n" +
                            "1. The wrong element may be mistakenly used in the test - it is necessary to change the name of the element [%1$s] to the actual one in a test step.%n" +
                            "2. Replace the element type in the mapping page with a class that implements the [%3$s] interface.%n" +
                            "3. Create your own class of element that implements [%3$s] interface and declare your element using this class at the mapping page.", title, requiredType.getName(), requiredType.getSimpleName()
                    )
            );
        }
        return requiredType.cast(initializedElement);
    }

    /**
     * Method performs search for field (element) at page mapping class by its name at @ElementName annotation
     * using reflection API.
     * @param cucumberElementTitle cucumber-name of element.
     * @return class field corresponding to the cucumber-name.
     */
    default Field getElement(String cucumberElementTitle) {
        Class<?> clazz = this.getClass();
        Field[] fields = new Field[0];

        // recursively aggregate list of fields (elements) from current class and its ancestors, until we hit the Object
        while (clazz != null && !clazz.getName().equals("java.lang.Object")) {
            fields = ArrayUtils.addAll(fields, clazz.getDeclaredFields());
            clazz = clazz.getSuperclass();
        }

        List<String> knownElements = new ArrayList<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(ElementName.class)) {
                ElementName fieldTitleAnnotation = field.getAnnotation(ElementName.class);
                String elementTitle = fieldTitleAnnotation.value();

                if (cucumberElementTitle.matches(elementTitle)) {
                    return field;
                }
                knownElements.add(elementTitle);
            }
        }
        String errorMessage = String.format("Syntax error: element - [%s] not found at the page or block element [%s]."
                        + "%nKnown elements are:%n%s",
                cucumberElementTitle, ((WithName) this).getName(), String.join(System.lineSeparator(), knownElements));
        throw new IllegalArgumentException(errorMessage);
    }

    default Object instantiateElementByType(Type type) {
        Object element;

        try {
            element = ((Class) type).newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(ex.getCause());
        }
        return element;
    }

    default Object performElementInitialization(Object element, String title, Field field) {
        if (!(element instanceof BaseWebElement)) {
            throw new ClassCastException("Mapping error. Element is not an instance of successor of BaseWebElement!");
        }

        BaseWebElement baseWebElement = (BaseWebElement) element;
        FindBy annotation = field.getAnnotation(FindBy.class);
        baseWebElement.setFindBy(annotation);
        baseWebElement.setTitle(title);
        return baseWebElement;
    }
}
