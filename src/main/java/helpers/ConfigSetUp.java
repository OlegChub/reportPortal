package helpers;

import config.Credentials;
import config.ProjectConfig;
import org.aeonbits.owner.ConfigFactory;

public class ConfigSetUp {
    private ConfigSetUp() {
    }

    private static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private static Credentials credentials = ConfigFactory.create(Credentials.class, System.getProperties());

    public static String getHost() {
        return config.host();
    }

    public static String getToken() {
        return credentials.token();
    }

    public static String getProjectName() {
        return config.projectName();
    }
}
