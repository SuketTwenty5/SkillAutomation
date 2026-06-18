package t5.ipe.cucumber.core.web.interfaces.core;

/**
 * Interface describes binding of webapp cucumber-name with certain host.
 *
 * Created by: EKruze
 * Date: 20/10/2023
 */
public interface SiteMap {

    /**
     * @param name cucumber-name of web application.
     * @param env target test environment name.
     * @return webapp host URL.
     */
    String getSiteUrl(String name, String env);

}
