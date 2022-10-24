package cucumber.steps;

import api.ApiClient;
import exeptions.FailedToLoginException;
import helpers.UserTokenGenerator;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LogManager.getLogger();
    public static ApiClient api = new ApiClient();
    private static UserTokenGenerator userToken = new UserTokenGenerator(api);

    @BeforeAll
    public static void setAccessToken() throws FailedToLoginException {
        logger.info("Setting token ...");
        userToken.setToken();
        logger.info("Token has been set successfully");
    }

    @Before
    public void start() {
        logger.info("Test is starting ...");
    }

    @After
    public void finish() {
        logger.info("Test is finished");
    }
}
