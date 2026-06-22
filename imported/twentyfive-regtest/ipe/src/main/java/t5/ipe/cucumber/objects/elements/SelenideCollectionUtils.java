package t5.ipe.cucumber.objects.elements;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.StaleElementReferenceException;

/**
 * Compatibility helpers for Selenide 7 collection APIs.
 */
public final class SelenideCollectionUtils {
    private SelenideCollectionUtils() {
    }

    public static int indexOf(Iterable<SelenideElement> elements, SelenideElement target) {
        int index = 0;
        for (SelenideElement element : elements) {
            if (sameElement(element, target)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private static boolean sameElement(SelenideElement left, SelenideElement right) {
        if (left == null || right == null) {
            return false;
        }
        if (left.equals(right)) {
            return true;
        }
        try {
            return left.toWebElement().equals(right.toWebElement());
        } catch (StaleElementReferenceException ignored) {
            return false;
        }
    }
}
