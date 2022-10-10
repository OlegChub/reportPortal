import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    protected void setUp() {
        logger.info("Test is starting ...");
    }

    @AfterEach
    protected void stopBrowser() {
        logger.info("Test has finished ...");
    }
}
