package t5.ipe.cucumber.core.web;

public class StepCounter {
    private static final ThreadLocal<io.cucumber.java.Scenario> currentScenario = new ThreadLocal<>();

    private static final ThreadLocal<Integer> stepCount = ThreadLocal.withInitial(() -> 0);

    private static final ThreadLocal<Integer> infoStepCount = ThreadLocal.withInitial(() -> 0);


    public static void reset() {
        stepCount.set(0);
        infoStepCount.set(0);
    }

    public static void increment() {
        stepCount.set(stepCount.get() + 1);
    }
    public static void incrementStep() {
        infoStepCount.set(infoStepCount.get() + 1);
    }

    public static int get() {
        return stepCount.get();
    }

    public static int getStep() {
        return infoStepCount.get();
    }

    // Set the scenario at the start of each scenario (in @Before hook)
    public static void setScenario(io.cucumber.java.Scenario scenario) {
        System.out.println("[ScenarioContext] Setting scenario: " + scenario.getName());
        currentScenario.set(scenario);
    }

    // Get the current scenario
    public static io.cucumber.java.Scenario getScenario() {
        io.cucumber.java.Scenario s = currentScenario.get();
        System.out.println("[ScenarioContext] Getting scenario: " + (s != null ? s.getName() : "null"));
        return s;
    }

    // Clear context at the end of each scenario (in @After hook)
    public static void clear() {
        System.out.println("[ScenarioContext] Clearing scenario context");
        currentScenario.remove();
    }
}
