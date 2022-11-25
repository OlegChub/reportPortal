package ui.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Optional;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

public class DriverManager {
    private final static String CHROME_DRIVER_PATH = "src\\main\\resources\\driver\\chromedriver.exe";
    private final static int IMPLICIT_WAIT_TIMEOUT = 5;
    private final static int PAGE_LOAD_TIMEOUT = 20;
    private final static Logger logger = LogManager.getLogger();

    private static ThreadLocal<WebDriver> ThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static void setupDriver() {
        logger.info("Setting driver...");
        System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_TIMEOUT));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        ThreadLocal.set(driver);
        logger.info("Driver was set successfully");
    }

    public static WebDriver getDriver() {
        return ThreadLocal.get();
    }

    public static void quitDriver() {
        logger.info("Quitting driver...");
        Optional.ofNullable(getDriver()).ifPresent(WebDriver ->
                WebDriver.quit()
        );
        logger.info("Driver was quited successfully");
    }
}
