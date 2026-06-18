package t5.ipe.cucumber.objects;
import org.junit.Assert;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Collectors;

public class DateDetector {
    // List of accepted date formats
    private static final List<DateTimeFormatter> DATE_FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("M/d/yy"),
            DateTimeFormatter.ofPattern("M/d/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd-MMM-yyyy")
    );

    public static boolean isDate(String input) {
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                LocalDate.parse(input, formatter);
                return true; // Parsing succeeded → it's a valid date
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }
        return false; // None of the formats matched
    }

    public static Set<String> convertToAllFormats(String inputDate) {
        Set<String> results = new LinkedHashSet<>();
        LocalDate parsedDate = null;

        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                parsedDate = LocalDate.parse(inputDate, formatter);
                break; // stop at first valid parse
            } catch (Exception ignored) {
            }
        }

        // ❌ Fail the test if date is invalid
        if (parsedDate == null) {
            Assert.fail("Invalid or unsupported date format: " + inputDate);
        }

        // ✅ Add all unique formatted dates
        for (DateTimeFormatter targetFormatter : DATE_FORMATS) {
            results.add(parsedDate.format(targetFormatter));
        }

        // Include original input
//        results.add("original: " + inputDate);

        return results;
    }

    public static String buildContainsTextCondition(String inputDate) {
        Set<String> dates = convertToAllFormats(inputDate);
        return dates.stream()
                .map(date -> "contains(text(),'" + date + "')")
                .collect(Collectors.joining(" or "));
    }
}
