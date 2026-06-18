package t5.ipe.cucumber.core.web.interfaces.element_interfaces.table;

import java.util.Map;

/**
 * Describes the functionality of entering a value into a table cell.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface EditableTable extends ReadableTable {
    default void setCellValue(String value, String columnName, Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void setCellValue(String value, String columnName, int index) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void deleteCellValue(String columnName, Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void deleteCellValue(String columnName, int index) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void fillNewRow(Map<String, String> attributes) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void fillSelectedRow(Map<String, String> attributes) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
