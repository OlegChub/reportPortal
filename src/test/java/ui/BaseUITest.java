package ui;

import api.ApiClient;
import controllers.WidgetController;
import exeptions.FailedToLoginException;
import helpers.UserLogin;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.RegisterExtension;
import ui.ScreenshotService.ScreenshotWatcher;
import ui.api.ApiSteps;
import ui.driver.DriverManager;
import ui.driver.wait.CustomFluentWait;
import ui.pages.LoginPage;

import static ui.logger.UILogger.LOGGER;

public class BaseUITest {
    private static ApiClient api = new ApiClient();
    private static UserLogin userLogin = new UserLogin(api);
    protected static ApiSteps apiSteps = new ApiSteps();
    protected static WidgetController widget = new WidgetController(api);
    protected static int widgetId1;
    protected static int widgetId2;


    @BeforeAll
    public static void setupDriver() throws FailedToLoginException {
        userLogin.login();
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
    public void beforeTestActions() {
        LOGGER.info("Test is starting ... ");
    }

    @AfterEach
    public void afterTestActions() {
        if (widgetId1 != 0 || widgetId2 != 0) {
            LOGGER.info("Removing test data ...");
            widget.deleteWidget(widgetId1).statusCode(HttpStatus.SC_OK);
            widget.deleteWidget(widgetId2).statusCode(HttpStatus.SC_OK);
            widgetId1 = 0;
            widgetId2 = 0;
            LOGGER.info("Test data successfully removed");
        }
        LOGGER.info("Test is finished");
    }

    @AfterAll
    public static void quitDriver() {
        DriverManager.quitDriver();
    }
}
