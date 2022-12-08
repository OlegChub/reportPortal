package ui.driverConfig;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverManager {
    private final static BrowserConfig CONFIG = ConfigFactory.create(BrowserConfig.class, System.getProperties());
    private final static int IMPLICIT_WAIT_TIMEOUT = 5;
    private final static int PAGE_LOAD_TIMEOUT = 60;
    private static final Logger LOGGER = LogManager.getLogger();

    private DriverManager() {
    }

    public static WebDriver setUpDriver() {
        LOGGER.info("Driver creation ...");
        WebDriverManager manager = WebDriverManager.getInstance(CONFIG.browser());
        manager.setup();
        WebDriver driver = manager.create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        LOGGER.info("Driver was created and properties were set");
        return driver;
    }
}
