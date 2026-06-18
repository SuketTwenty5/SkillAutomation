package t5.ipe.cucumber.core.web.interfaces.element_interfaces.table;

import java.util.Map;

/**
 * Defines the base table interface that contains utility methods. Should not be implemented directly.
 * Use inherited interfaces instead
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface BaseTable {
    default int getColumnIndexByName(String columnName) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

//    default int getColumnIndexByNameWarning(String columnName) {
//        throw new UnsupportedOperationException("Method is not implemented yet");
//    }

    default int getRowIndex(Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

//    default int getRowIndexWarning(Map<String, String> filter) {
//        throw new UnsupportedOperationException("Method is not implemented yet");
//    }

    default int getRowIndex(String columnName,Map<String, String> filter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default int getRowIndexByName(String rowName) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default int getRowsCount() {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default int getRowsCount(String columnName) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void hoverCellAndClickGearMenu(String columnName, Map<String, String> rowFilter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
