package ui.driver;

import org.openqa.selenium.WebDriver;
import ui.driverConfig.DriverManager;

import java.util.Optional;

import static ui.logger.UILogger.LOGGER;

public class DriverProvider {

    private static ThreadLocal<WebDriver> ThreadLocal = new ThreadLocal<>();

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (ThreadLocal.get() == null) {
            LOGGER.info("Setting driver...");
            WebDriver driver = DriverManager.setUpDriver();
            ThreadLocal.set(driver);
            LOGGER.info("Driver was set successfully");
        }
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
