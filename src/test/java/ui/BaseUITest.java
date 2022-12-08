package ui;

import api.ApiClient;
import exeptions.FailedToLoginException;
import helpers.UserLogin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import ui.api.ApiSteps;
import ui.driver.DriverProvider;
import ui.pages.LoginPage;
import ui.screenshotService.ScreenshotWatcher;

public class BaseUITest {
    protected int widgetId1;
    protected int widgetId2;
    private static ApiClient api = new ApiClient();
    private static UserLogin userLogin = new UserLogin(api);
    protected static ApiSteps apiSteps = new ApiSteps();
    private static final Logger LOGGER = LogManager.getLogger();

    @RegisterExtension
    ScreenshotWatcher watcher = new ScreenshotWatcher();

    @BeforeAll
    public static void setupDriver() throws FailedToLoginException {
        userLogin.login();
        LOGGER.info("Opening login page and login ...");
        new LoginPage().openLoginPage().enterCredentials().clickLoginButton();
        LOGGER.info("Successfully login as admin");
    }

    @BeforeEach
    public void beforeTestActions() {
        LOGGER.info("Test is starting ... ");
    }

    @AfterEach
    public void afterTestActions() {
        if (widgetId1 != 0 || widgetId2 != 0) {
            LOGGER.info("Removing test data ...");
            apiSteps.deleteWidgetWithId(widgetId1);
            apiSteps.deleteWidgetWithId(widgetId2);
            widgetId1 = 0;
            widgetId2 = 0;
            LOGGER.info("Test data successfully removed");
        }
        LOGGER.info("Test is finished");
    }

    @AfterAll
    public static void quitDriver() {
        DriverProvider.quitDriver();
    }
}
