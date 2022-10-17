package testsWithTestNG;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestNG {
    private static final Logger logger = LogManager.getLogger();

    @BeforeMethod
    protected void start() {
        logger.info("Test is starting ...");
    }

    @AfterMethod
    protected void finish() {
        logger.info("Test has finished ...");
    }
}
