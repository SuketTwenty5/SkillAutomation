package t5.ipe.cucumber.core.web.interfaces.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotation is intended for storing and providing page name and page URL.
 * All webpage mapping classes should be marked with this annotation.
 * Created by: EKruze
 * Date: 20/10/2023
 */
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface WebPage {

    /**
     * @return cucumber-name of page.
     */
    String value();

    /**
     * @return resource path (excluding protocol and host). Required for explicit transfer to destination page.
     */
    String resourcePath() default "";

    /**
     * @return regular expression, which defines URL template of resource (excluding protocol and host), which is used
     * for matching current browser web address for current web page identification. Template is required because some
     * of URL paths are able to contain dynamic fragments.
     */
    String urlTemplate();


    String tabName() default "";
}
