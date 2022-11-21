package httpClientTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class HttpClientBaseTest {
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    protected void start() {
        logger.info("Test is starting ...");
    }

    @AfterEach
    protected void finish() {
        logger.info("Test is finished");
    }
}
