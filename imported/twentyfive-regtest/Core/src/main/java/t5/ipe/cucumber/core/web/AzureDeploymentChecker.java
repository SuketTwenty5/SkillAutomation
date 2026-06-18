package t5.ipe.cucumber.core.web;

/**
 * Local-safe deployment guard for the shared consultant runner.
 *
 * The original project can replace this class with an implementation that checks
 * Azure deployment state. In this distributed runner, the guard is intentionally
 * inert so local UI tests compile and run without external credentials.
 */
public final class AzureDeploymentChecker {
    private AzureDeploymentChecker() {
    }

    public static void abortIfDeploymentInProgress() {
        if (Boolean.getBoolean("deployment.guard.enabled")) {
            System.out.println("[AzureDeploymentChecker] deployment.guard.enabled=true, but no external guard is bundled.");
        }
    }
}
