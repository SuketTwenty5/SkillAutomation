package t5.ipe.cucumber.objects;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class NumberStringDetector {

    public static boolean isNumberWithComma(String testStrings) {
        // Regex to match numbers with commas and optional decimals
        String regex = "^\\d{1,3}(,\\d{3})*(\\.\\d+)?$";
        return Pattern.matches(regex, testStrings);
    }
    public static boolean isNumber(String testStrings) {
        String type =  detectNumberType(testStrings);
        return type.equals("Integer") || type.equals("Decimal");
    }

    static String format(String value) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Long.parseLong(value));
    }

    public static String buildXpathContainsBothIntegerDouble(String value) {
        value = value.replaceAll("\\s*hours\\s*", "").trim();
        return "contains(text(), '" + value + "') or contains(text(), '" + format(value) + ".00')";
    }


    public static String detectNumberType(String value) {
        value = value.replaceAll("\\s*hours\\s*", "").trim();
        value = value.replace(",", "");
        if (value.trim().isEmpty()) {
            return "Invalid number";
        }

        try {
            BigDecimal bd = new BigDecimal(value.trim());

            if (bd.scale() <= 0) {
                return "Integer";
            } else {
                return "Decimal";
            }
        } catch (NumberFormatException e) {
            return "Invalid number";
        }
    }

    public static double extractDouble(String numberString) {
        // Remove commas
        String cleaned = numberString.replaceAll(",", "");
        // Convert to double
        cleaned = cleaned.replaceAll("\\s*hours\\s*", "").trim();
        try {
            return Double.parseDouble(cleaned);
        }catch (Exception e){
            throw new NumberFormatException("❌ Unable to convert string to double: " + numberString);
        }
    }
}
