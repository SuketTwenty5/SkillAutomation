package t5.ipe.cucumber.core.web.interfaces.annotations;

import java.lang.annotation.*;

/**
 * Annotation for setting cucumber-name for page-mapping element (field of mapping class).
 * Created by: EKruze
 * Date: 20/10/2023
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ElementName {

    /**
     * @return name of element.
     */
    String value();
}