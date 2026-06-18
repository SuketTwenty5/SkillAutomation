package t5.ipe.cucumber.core.web;

import io.cucumber.java.Scenario;

/**
 * Local-safe ticket hook for the shared consultant runner.
 *
 * The original project can replace this class with Jira/Azure ticket creation.
 * This version avoids external service calls and secrets during consultant runs.
 */
public final class TicketHandler {
    private TicketHandler() {
    }

    public static void handleTicketCreation(boolean scenarioPassed, Scenario scenario) {
        if (Boolean.getBoolean("ticket.creation.enabled")) {
            String scenarioName = scenario == null ? "unknown scenario" : scenario.getName();
            System.out.println("[TicketHandler] ticket.creation.enabled=true, but ticket creation is not bundled. Scenario: "
                    + scenarioName + ", passed=" + scenarioPassed);
        }
    }
}
