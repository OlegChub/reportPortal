import api.ApiClient;
import exeptions.FailedToLoginException;
import helpers.UserLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class BaseTest {
    private static final Logger logger = LogManager.getLogger();
    public static ApiClient api = new ApiClient();
    private static UserLogin userLogin = new UserLogin(api);

    @BeforeAll
    protected static void setAccessToken() throws FailedToLoginException {
        logger.info("Setting token ...");
        userLogin.login();
        logger.info("Token has been set successfully");
    }

    @BeforeEach
    protected void start() {
        logger.info("Test is starting ...");
    }

    @AfterEach
    protected void finish() {
        logger.info("Test is finished");
    }
}
