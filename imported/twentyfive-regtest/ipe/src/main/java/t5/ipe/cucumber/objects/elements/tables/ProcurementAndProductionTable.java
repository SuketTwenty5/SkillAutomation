package t5.ipe.cucumber.objects.elements.tables;

import static t5.ipe.cucumber.objects.elements.SelenideCollectionUtils.indexOf;

import com.codeborne.selenide.SelenideElement;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProcurementAndProductionTable extends LaborTable {
    private static final String HEADER_COLUMN_XPATH
            = "//div[contains(@class, 'x-tabpanel-child') and not(contains(@class,'x-hidden-offsets'))]//div[@aria-hidden='false' and @role='columnheader']//span[@class='x-column-header-text-inner']";
    private static final String CELL_XPATH = "(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//table//tr[contains(@class,'x-grid-row')]//td";

    private static final String ALL_ROWS_XPATH = "//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//tr[contains(@class,'x-grid-row')]";

    @Override
    public int getRowIndex(Map<String, String> filter) {
        String rowXPath = appendXPathByFilter(ALL_ROWS_XPATH, filter);
        SelenideElement element = $x(rowXPath);

        if (!element.exists())
            return -1;

        return indexOf($$x(ALL_ROWS_XPATH), element.scrollIntoView(true));
    }

    @Override
    public boolean isRowPresent(Map<String, String> filter) {
        return getRowIndex(filter) >= 0;
    }

    String appendXPathByFilter(String initialXPath, Map<String, String> rowFilter) {
        StringBuilder stringBuilder = new StringBuilder(initialXPath);
        for (Map.Entry<String, String> entry : rowFilter.entrySet()) {
            String columnName = entry.getKey();
            String cellValue = entry.getValue();
            int columnIndex = getColumnIndexByName(columnName);
            stringBuilder.append("[..//td[").append(columnIndex + 1).append("]//..//*[contains(text(), '").append(cellValue).append("')]]");
        }
        return stringBuilder.toString();
    }

}