package t5.ipe.cucumber.core.web.util.generator;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Used at GeneratorUtils for definition of generation algorithm.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public enum PatternType {
    CUR_DATE, GEN_UUID, UNKNOWN;

    public static String printValues() {
        return Arrays.stream(values())
                .map(Enum::name)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
