package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

import java.util.List;

/**
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface Selectable {

    /**
     * Selects corresponding item.
     *
     * @param text - item name.
     */
    default void select(String text) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    /**
     * Unselects corresponding item.
     *
     * @param text - item name.
     */
    default void deSelect(String text) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    /**
     * Checks the state of corresponding item.
     *
     * @param text - item name.
     * @return {@code true} - if corresponding item is selected.
     */
    default boolean isSelected(String text) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    /**
     * Checks the presence of corresponding item.
     *
     * @param text - item name.
     * @return {@code true} - if corresponding item is present.
     */
    default boolean isPresent(String text) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    /**
     * Returns the item list.
     *
     * @return list of presented items.
     */
    default List<String> getElements() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default int getCount() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
