package t5.ipe.cucumber.core.web.steps;

import t5.ipe.cucumber.core.web.util.DataContainer;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.util.Map;

import static t5.ipe.cucumber.core.web.util.generator.GeneratorUtils.generateValueIfContainsPatterns;

/**
 * Steps that implements actions with test variables.
 * <p>
 * Created by: EKruze
 * Date: 20/10/2023
 */
public class VariablesSteps {

    @And("^value of variable '(.+)' equals '(.+)'$")
    public void valueOfVariableEquals(String varName, String expectedText) {
        String resolvedText = DataContainer.resolveVariablesInText(varName);
        Assert.assertEquals(expectedText, resolvedText);
    }

    @And("^save value '(.+)' to the variable '(.+)'$")
    public void saveTextToTheVariable(String textToSave, String varName) {
        DataContainer.storeVariable(varName, DataContainer.resolveVariablesInText(textToSave));
    }

    @And("^save values to the variables:$")
    public void saveValuesVariables(Map<String, String> table) {
        for (String varName : table.keySet()) {
            String tableValue = table.get(varName);
            DataContainer.storeVariable(varName, DataContainer.resolveVariablesInText(tableValue));
        }
    }

    @Given("^generate values and store into the variables:$")
    public void generateValuesAndStoreIntoTheVariables(Map<String, String> variablesAndPatterns) {
        for (Map.Entry<String, String> varEntry : variablesAndPatterns.entrySet()) {
            String generatedValue = generateValueIfContainsPatterns(varEntry.getValue());
            DataContainer.storeVariable(varEntry.getKey(), generatedValue);
        }
    }
}
