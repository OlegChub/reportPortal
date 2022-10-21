package helpers;

import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ConfigSetUp {
    private ConfigSetUp() {
    }

    private static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static String getHost() {
        return config.host();
    }

}
