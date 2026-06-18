package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Interface describes behavior of toggles, tabs and checkboxes.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Toggle {
    default void activate() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default void deactivate() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default boolean isActivated() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
