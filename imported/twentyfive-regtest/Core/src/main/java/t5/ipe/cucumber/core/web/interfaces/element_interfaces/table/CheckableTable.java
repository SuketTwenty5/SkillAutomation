package t5.ipe.cucumber.core.web.interfaces.element_interfaces.table;

import java.util.Map;

/**
 * Interface describes behavior of toggles inside of table cells (example - table on Commodity Elements tab).
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface CheckableTable extends BaseTable {
    default void checkCell(String columnName, Map<String, String> rowFilter) throws InterruptedException {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default void checkCell(String columnName,int index) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
    default void uncheckCell(String columnName, Map<String, String> rowFilter) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }

    default void checkEmptyCell(String columnName) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
