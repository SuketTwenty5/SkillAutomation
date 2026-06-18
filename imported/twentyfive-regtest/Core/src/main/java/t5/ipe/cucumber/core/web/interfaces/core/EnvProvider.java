package t5.ipe.cucumber.core.web.interfaces.core;

/**
 * Describes functionality which provides current env name.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface EnvProvider {

    /**
     * @return project name of current environment.
     */
    String getCurrentEnv();
}
