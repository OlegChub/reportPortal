package ui.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ui.driverConfig.DriverManager;

import java.util.Optional;

public class DriverProvider {

    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger();

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (threadLocal.get() == null) {
            LOGGER.info("Setting driver...");
            WebDriver driver = DriverManager.setUpDriver();
            threadLocal.set(driver);
            LOGGER.info("Driver was set successfully");
        }
        return threadLocal.get();
    }

    public static void quitDriver() {
        LOGGER.info("Quitting driver...");
        Optional.ofNullable(getDriver()).ifPresent(WebDriver ->
                WebDriver.quit()
        );
        LOGGER.info("Driver was quited successfully");
    }
}
