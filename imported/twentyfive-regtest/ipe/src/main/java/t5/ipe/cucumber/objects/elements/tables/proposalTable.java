package t5.ipe.cucumber.objects.elements.tables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import t5.ipe.cucumber.core.web.elements.BaseWebElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.CheckableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.EditableTable;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.table.ReadableTable;
import t5.ipe.cucumber.core.web.util.AllureUtils;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.codeborne.selenide.Selenide.$$x;

public class proposalTable extends BaseWebElement implements EditableTable, ReadableTable, CheckableTable {
    private static final String HEADER_COLUMN_XPATH="//div[@aria-hidden='false' and @role='columnheader']";
    @Override
    public int getColumnIndexByName(String columnName) {
        ElementsCollection headers = $$x(HEADER_COLUMN_XPATH);
        Optional<SelenideElement> headerOptional = headers
                .stream()
                .filter(x -> x.getText().equals(columnName))
                .findFirst();
        if (headerOptional.isPresent()) {
            SelenideElement header = headerOptional.get();
            return headers.indexOf(header);
        } else {
            AllureUtils.saveScreenshot();
            AllureUtils.logActionF("Column with name %s is not displayed on the page. Perhaps the workbench table did not appear. Please check the screenshot.", columnName);
            throw new NoSuchElementException("Column with name " + columnName + " not found on the page.");
        }
    }
}
