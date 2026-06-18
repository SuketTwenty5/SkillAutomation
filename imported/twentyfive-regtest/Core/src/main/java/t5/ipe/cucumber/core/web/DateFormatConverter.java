package t5.ipe.cucumber.core.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateFormatConverter {
    private static final String[] POSSIBLE_FORMATS = {
            "MM/dd/yyyy",
            "M/d/yy",
            "M/d/yyyy",
            "dd-MM-yyyy",
            "yyyy-MM-dd",
            "d/M/yyyy",
            "MM-dd-yy",
            "dd/MM/yy",
            "yyyy/MM/dd",
            "MMM d, yyyy",
            "MMMM d, yyyy"
    };

    private static final SimpleDateFormat OUTPUT_FORMAT = new SimpleDateFormat("M/d/yy");

    public static String convertDate(String input) {
        Date parsedDate = parseDate(input);
        System.out.printf("Attempting to convert date %s. Parsed date: %s%n", input, parsedDate);
        if (parsedDate != null) {
            return OUTPUT_FORMAT.format(parsedDate);
        } else {
            return "Not a valid date: " + input;
        }
    }

    public static List<String> convertDates(List<String> inputDates) {
        List<String> result = new ArrayList<>();

        for (String dateStr : inputDates) {
            Date parsedDate = parseDate(dateStr);
            if (parsedDate != null) {
                result.add(OUTPUT_FORMAT.format(parsedDate));
            } else {
                result.add("Invalid: " + dateStr);
            }
        }

        return result;
    }
    public static Date parseDate(String dateStr) {
        for (String format : POSSIBLE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                sdf.setLenient(false);
                return sdf.parse(dateStr);
            } catch (ParseException ignored) {
            }
        }
        return null; // no match
    }
}
