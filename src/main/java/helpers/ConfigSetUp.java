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

    public static String getScheme() {
        return config.scheme();
    }

    public static String getLocalhost() {
        return config.localhost();
    }

    public static int getPort() {
        return config.port();
    }
}
