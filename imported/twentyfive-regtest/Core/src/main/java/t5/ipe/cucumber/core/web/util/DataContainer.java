package t5.ipe.cucumber.core.web.util;

import org.apache.commons.text.StringSubstitutor;
import org.mariuszgromada.math.mxparser.Expression;
import t5.ipe.cucumber.core.web.DateFormatConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class is responsible for storing, reading (resolving), concatenating test-variables
 * and calculating math expressions with test-variables.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class DataContainer {

    public static final String VARIABLE_PATTERN = "\\B\\$\\w+";
    public static final String MATH_OPS_PATTERN = "\\s*[+\\-*/]\\s*";
    public static final String CONCAT_PATTERN = "\\s*&\\s*";


    private static final Map<String, Object> storage = new HashMap<>();

    public static void storeVariable(String varName, String value) {
        if (!varName.startsWith("$")) {
            throw new RuntimeException("Storing variables should start with '$' symbol.");
        }
        storage.put(varName, value);
        AllureUtils.logActionF("Stored variable %s with value %s", varName, value);
    }

    public static String resolveVariablesInText(String text) {
        System.out.println(text);
        int varNumber = countVariables(text);
        switch (varNumber) {
            case 0:
                if (text.matches(".* \\((shared|preferred|shared & preferred)\\)$")) {
                    text = text.replaceAll(" \\((shared|preferred|shared & preferred)\\)$", "");
                }
                if(DateFormatConverter.parseDate(text) != null) {
                    System.out.printf("Text %s is recognized as date. Attempting to convert it to the format M/d/yy", text);
                    String originalText;
                    if(text.contains("-")) {
                        originalText = text;
                    }else {
                        originalText = DateFormatConverter.convertDate(text);
                    }
                    System.out.printf("Converted date %s to %s", text, originalText);
                    return originalText;
                }
                return text;
            case 1:
                return resolveSingleVariable(text);

            default:
                return resolveGroupOfVariables(text);
        }
    }

    private static int countVariables(String text) {
        Pattern variablesPattern = Pattern.compile(VARIABLE_PATTERN);
        Matcher matcher = variablesPattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    private static String resolveSingleVariable(String text) {
        if (!storage.containsKey(text)) {
            throw new IllegalArgumentException("The value of variable " + text + " has not been saved yet.");
        }
        return (String) storage.get(text);
    }

    private static String resolveGroupOfVariables(String text) {
        if (!text.contains("&")) {
            return resolveMathExpression(text);
        }
        String[] arrayOfVariables = text.split(CONCAT_PATTERN);
        StringBuilder sb = new StringBuilder();
        for (String variable : arrayOfVariables) {
            sb.append(resolveSingleVariable(variable));
        }
        return sb.toString();
    }

    private static String resolveMathExpression(String text) {
        String[] arrayOfVariables = text.split(MATH_OPS_PATTERN);

        Map<String, String> valuesToSubstitute = new HashMap<>();
        for (String variable : arrayOfVariables) {
            valuesToSubstitute.put(variable, resolveSingleVariable(variable));
        }

        String textForSubstitution = text.replaceAll("(" + VARIABLE_PATTERN + ")", "${$1}");
        String preparedMathExpression = StringSubstitutor.replace(textForSubstitution, valuesToSubstitute);
        Expression expression = new Expression(preparedMathExpression);
        if (!expression.checkSyntax()) {
            throw new IllegalArgumentException("Broken syntax of arithmetic expression: " + text);
        }
        return String.valueOf(expression.calculate());
    }

}
