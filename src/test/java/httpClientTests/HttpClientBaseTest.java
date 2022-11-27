package httpClientTests;

import httpclient.log.HttpClientLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class HttpClientBaseTest {

    @BeforeEach
    protected void start() {
        HttpClientLogger.logger.info("Test is starting ...");
    }

    @AfterEach
    protected void finish() {
        HttpClientLogger.logger.info("Test is finished");
    }
}
