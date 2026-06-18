package t5.ipe.cucumber.core.web.interfaces.element_interfaces;

/**
 * Describes date picking functionality.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface DatePickable {
    default void setYearMonthAndDay(String year, String month, String day) {
        throw new UnsupportedOperationException("Method is not implemented yet");
    }
}
