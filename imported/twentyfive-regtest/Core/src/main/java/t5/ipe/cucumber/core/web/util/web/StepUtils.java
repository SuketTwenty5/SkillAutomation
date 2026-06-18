package t5.ipe.cucumber.core.web.util.web;

import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Toggle;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.DatePickable;

import java.util.Locale;

/**
 * Library of helper methods for action steps.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class StepUtils {

    public static void setDateInSimpleFormatAtDatePicker(DatePickable elementInstance, String dateString) {
        String[] yearAndMonthAndDate = dateString.split("/");
        String day = "";
        String month = "";
        String year;
        switch (yearAndMonthAndDate.length) {
            case 3:
                day = yearAndMonthAndDate[2];
                // falls through
            case 2:
                month = yearAndMonthAndDate[1];
                // falls through
            case 1:
                year = yearAndMonthAndDate[0];
                break;
            default:
                throw new RuntimeException("Broken format of the date [" + dateString + "]. Correct format is: yyyy/MM/dd");
        }
        elementInstance.setYearMonthAndDay(year, month, day);
    }

    public static void setValueToToggle(Toggle element, String valueToSet) {
        switch (valueToSet.toLowerCase(Locale.ROOT)) {
            case "check":
                element.activate();
                break;

            case "uncheck":
                element.deactivate();
                break;

            default:
                throw new RuntimeException("Unknown value to set for Toggle element instance. Allowed values are: CHECK, UNCHECK.");
        }
    }
}
