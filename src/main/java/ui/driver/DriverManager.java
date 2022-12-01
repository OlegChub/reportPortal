package ui.driver;

import org.openqa.selenium.WebDriver;
import ui.driverConfig.DriverProperties;

import java.time.Duration;
import java.util.Optional;

import static ui.logger.UILogger.LOGGER;

public class DriverManager {
    private final static int IMPLICIT_WAIT_TIMEOUT = 5;
    private final static int PAGE_LOAD_TIMEOUT = 60;

    private static ThreadLocal<WebDriver> ThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setupDriver() {
        LOGGER.info("Setting driver...");
        WebDriver driver = DriverProperties.setUpDriverWithProperties();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        ThreadLocal.set(driver);
        LOGGER.info("Driver was set successfully");
    }

    public static WebDriver getDriver() {
        return ThreadLocal.get();
    }

    public static void quitDriver() {
        LOGGER.info("Quitting driver...");
        Optional.ofNullable(getDriver()).ifPresent(WebDriver ->
                WebDriver.quit()
        );
        LOGGER.info("Driver was quited successfully");
    }
}
