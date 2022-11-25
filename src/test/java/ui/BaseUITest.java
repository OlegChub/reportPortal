package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import ui.ScreenshotService.ScreenshotWatcher;
import ui.driver.DriverManager;
import ui.driver.wait.CustomFluentWait;
import ui.pages.LoginPage;

public class BaseUITest {
    protected static final Logger LOGGER = LogManager.getLogger();

    @BeforeAll
    public static void setupDriver() {
        DriverManager.setupDriver();
        CustomFluentWait.setCustomFluentWait();
        LOGGER.info("Opening login page and login ...");
        var loginPage = new LoginPage();
        loginPage.openLoginPage().enterCredentials().clickLoginButton();
        LOGGER.info("Successfully login as admin");
    }

    @RegisterExtension
    ScreenshotWatcher watcher = new ScreenshotWatcher();

    @BeforeEach
    public void beforeTestLogging() {
        LOGGER.info("Test is starting ...");
    }

    @AfterEach
    public void afterTestLogging() {
        LOGGER.info("Test is finished");
    }

    @AfterAll
    public static void quitDriver() {
        DriverManager.quitDriver();
    }
}
