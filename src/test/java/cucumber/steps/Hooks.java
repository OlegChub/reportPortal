package cucumber.steps;

import api.ApiClient;
import exeptions.FailedToLoginException;
import helpers.UserLogin;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger LOGGER = LogManager.getLogger();
    private static ApiClient api = new ApiClient();
    private static UserLogin loginAsUser = new UserLogin(api);

    @BeforeAll
    public static void setAccessToken() throws FailedToLoginException {
        LOGGER.info("Setting token ...");
        loginAsUser.login();
        LOGGER.info("Token has been set successfully");
    }

    @Before
    public void start() {
        LOGGER.info("Test is starting ...");
    }

    @After
    public void finish() {
        LOGGER.info("Test is finished");
    }
}
