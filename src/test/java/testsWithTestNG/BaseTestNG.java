package testsWithTestNG;

import api.ApiClient;
import exeptions.FailedToLoginException;
import helpers.UserTokenGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTestNG {
    private static final Logger logger = LogManager.getLogger();
    public static ApiClient api = new ApiClient();
    private static UserTokenGenerator userTokenGenerator = new UserTokenGenerator(api);

    @BeforeClass
    protected static void setAccessToken() throws FailedToLoginException {
        logger.info("Setting token ...");
        userTokenGenerator.setToken();
        logger.info("Token has been set successfully");
    }

    @BeforeMethod
    protected void start() {
        logger.info("Test is starting ...");
    }

    @AfterMethod
    protected void finish() {
        logger.info("Test has finished ...");
    }
}
