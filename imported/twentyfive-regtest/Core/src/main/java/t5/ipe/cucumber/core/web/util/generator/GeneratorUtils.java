package t5.ipe.cucumber.core.web.util.generator;

import org.apache.commons.text.StringSubstitutor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static t5.ipe.cucumber.core.web.util.generator.PatternType.UNKNOWN;

/**
 * Contains value generation functionality.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class GeneratorUtils {

    private static final String ERROR_MESSAGE_TEMPLATE = "Unknown pattern [%s] at the text [%s].%nAvailable patterns are:%n%s";
    private static final String PATTERN_ERROR_MESSAGE_TEMPLATE = "Error in pattern [%s]";
    private static final String ERROR_PATTERN_TEMPLATE = "Error in date time pattern %s";

    /**
     * Generates new string by template.<br/>
     * <b>Template</b> - it is the text, that should contain one or more <b>generator patterns</b>. Optionally template can contain
     * static text.<br/><br/>
     * <b>Generator pattern</b> it is the predefined keyword of enum which defines type (or algorithm) of generation in next
     * format: <br/>${GEN_PATTERN_KEYWORD}<br/><br/>
     * Part of generator patterns can contain options part after keyword, delimited by comma, for example:
     * <br/>${GEN_PATTERN_KEYWORD, GENERATOR_OPTIONS}<br/><br/>
     * Now there are available two generator patterns: CUR_DATE and GEN_UUID<br/><br/>
     * Examples of usage:<br/><br/>
     * ${CUR_DATE,yyyy.MM.dd hh:mm} - generates current date in format "yyyy.MM.dd hh:mm"<br/><br/>
     * ${CUR_DATE,+01/02/03;yyyy-MM-dd} - generates date relative to current which has positive offset of 3 years 2 months and 1 day<br/><br/>
     * ${CUR_DATE,-01/00/00;dd} - generates day number of yesterday (negative offset of one day)<br/><br/>
     *
     * ${GEN_UUID} - generates random UUID<br/><br/>
     * Also available format of template:<br/><br/>
     * Deal_${CUR_DATE,yyyy.MM.dd}_${GEN_UUID}
     *
     * @param fullTemplate generator template.
     * @return generated value
     */
    public static String generateValueIfContainsPatterns(String fullTemplate) {
        Pattern regexPattern = Pattern.compile("\\$\\{([^$]+)}");
        Matcher matcher = regexPattern.matcher(fullTemplate);
        List<String> extractedPatterns = new ArrayList<>();
        while (matcher.find()) {
            extractedPatterns.add(matcher.group(1));
        }
        System.out.println(extractedPatterns);

        Map<String, String> valuesToSubstitute = new HashMap<>();
        for (String extractedPattern : extractedPatterns) {
            String resolvedPattern = generateValueByPattern(fullTemplate, extractedPattern);
            valuesToSubstitute.put(extractedPattern, resolvedPattern);
        }

        return StringSubstitutor.replace(fullTemplate, valuesToSubstitute);
    }

    private static String generateValueByPattern(String fullTemplate, String genPattern) {
        String returnValue;
        String patternKeyString = extractPatternKeyString(genPattern);
        String errorMessage = String.format(ERROR_MESSAGE_TEMPLATE, genPattern, fullTemplate, PatternType.printValues());

        PatternType patternType = getPatternTypeFromString(patternKeyString);

        switch (patternType) {
            case GEN_UUID:
                returnValue = UUID.randomUUID().toString();
                break;

            case CUR_DATE:
                returnValue = getZonedDateFormat(getDatePattern(genPattern));
                break;

            default:
                throw new RuntimeException(errorMessage);
        }
        return returnValue;
    }

    private static String getDatePattern(String pattern) {
        return getSecondDateFromPattern(pattern);
    }

    private static String getSecondDateFromPattern(String pattern) {
        try {
            return pattern.split(",")[1].trim();
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new RuntimeException(String.format("Second part of pattern [%s] is not present. ", pattern));
        }
    }

    private static String getZonedDateFormat(String patternWithOffset) {
        String[] genPattenParts = patternWithOffset.split(";");
        String dateTimeFormat = genPattenParts[0];
        String[] offsetElements;
        String operation;
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        if (genPattenParts.length > 1) {
            dateTimeFormat = genPattenParts[1];
            String offsetPart = genPattenParts[0];
            operation = offsetPart.substring(0, 1);

            String offsetPattern = offsetPart.substring(1);
            offsetElements = offsetPattern.split("/");
            Long dayOffset = Long.parseLong(offsetElements[0]);
            Long monthsOffset = Long.parseLong(offsetElements[1]);
            Long yearsOffset = Long.parseLong(offsetElements[2]);

            zonedDateTime = modifyZonedDateTime(operation, dayOffset, monthsOffset, yearsOffset);
        }

        String formattedDate;
        try {
            formattedDate = zonedDateTime.format(DateTimeFormatter.ofPattern(dateTimeFormat));
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            throw new RuntimeException(String.format(ERROR_PATTERN_TEMPLATE, patternWithOffset));
        }
        return formattedDate;
    }

    private static ZonedDateTime modifyZonedDateTime(String operation, Long dayOffset, Long monthsOffset, Long yearsOffset) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        switch (operation) {
            case "+":
                zonedDateTime = zonedDateTime.plusDays(dayOffset).plusMonths(monthsOffset).plusYears(yearsOffset);
                break;

            case "-":
                zonedDateTime = zonedDateTime.minusDays(dayOffset).minusMonths(monthsOffset).minusYears(yearsOffset);
                break;

            default:
                throw new IllegalArgumentException(
                        String.format("Unknown type of date operation %s", operation));
        }
        return zonedDateTime;
    }

    private static PatternType getPatternTypeFromString(String patternName) {
        try {
            return PatternType.valueOf(patternName);
        } catch (IllegalArgumentException iae) {
            return UNKNOWN;
        }
    }

    private static String extractPatternKeyString(String pattern) {
        if (!pattern.contains(",")) {
            return pattern;
        }

        try {
            // if it is a date template or template for random text (i.e. contains ",") then into switch statement
            // a part before comma is used
            return pattern.split(",")[0];
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new RuntimeException(String.format(PATTERN_ERROR_MESSAGE_TEMPLATE, pattern));
        }
    }
}
