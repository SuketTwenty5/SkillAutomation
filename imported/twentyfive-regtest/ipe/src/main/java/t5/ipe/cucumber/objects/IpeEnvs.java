package t5.ipe.cucumber.objects;

import t5.ipe.cucumber.core.web.interfaces.core.EnvProvider;
import org.springframework.stereotype.Component;


import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * Ipe implementation of EnvProvider.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
@Component
public class IpeEnvs implements EnvProvider {

    @Override
    public String getCurrentEnv() {
        String envValue = System.getProperty("env");
        if (isEmpty(envValue)) {
            throw new RuntimeException("Env value is not set! " +
                    "Specify -Denv=<env> at maven command line.");
        }
        return envValue;
    }
}
