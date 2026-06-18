package t5.ipe.cucumber.core.web;

public class PreviousWindow {

    private static final ThreadLocal<String> value = new ThreadLocal<>();

    private PreviousWindow() {}

    public static void setValue(String v) {
        value.set(v);
    }

    public static String getValue() {
        return value.get();
    }

    public static void clear() {
        value.remove();
    }
}