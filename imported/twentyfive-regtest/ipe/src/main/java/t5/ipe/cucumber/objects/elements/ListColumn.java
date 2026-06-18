package t5.ipe.cucumber.objects.elements;

import t5.ipe.cucumber.core.web.elements.TextElement;
import t5.ipe.cucumber.core.web.interfaces.element_interfaces.Selectable;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ListColumn extends TextElement implements Selectable {
    @Override
    public List<String> getElements(){
        String childXpath = toXpath() +"/*";
        return $$x(childXpath).texts();
    }


    @Override
    public int getCount() {
        String childXpath = toXpath();
        return $$x(childXpath).size();
    }
}
