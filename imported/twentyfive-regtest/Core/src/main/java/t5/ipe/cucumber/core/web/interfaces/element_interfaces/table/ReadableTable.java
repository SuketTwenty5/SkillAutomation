package t5.ipe.cucumber.core.web.interfaces.element_interfaces.table;

import java.util.Map;

/**
 * Describes the functionality of reading a value from a table.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface ReadableTable extends BaseTable {
    default String readCellValue(String columnName) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default String readCellValue(String columnName, String index) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default String readCellValue(String columnName, Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default boolean isRowPresent(Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default boolean isRowPresentWarning(Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default boolean isRowPresent(String columnName, Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default boolean isChecked(String columnName, Map<String, String> filter){
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
