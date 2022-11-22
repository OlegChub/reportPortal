package ui.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.Optional;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

public class DriverManager {
    private final static String CHROME_DRIVER_PATH = "src\\main\\resources\\driver\\chromedriver.exe";
    private final static int IMPLICIT_WAIT_TIMEOUT = 5;
    private final static int PAGE_LOAD_TIMEOUT = 20;
    private final static int FLUENT_WAIT_TIMEOUT = 10;
    private final static int FLUENT_WAIT_POLLING_TIME = 1;
    private final static Logger logger = LogManager.getLogger();

    private static ThreadLocal<WebDriver> ThreadLocal = new ThreadLocal<>();
    private static FluentWait fluentWait;

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
        setFluentWait();
        logger.info("Driver was set successfully");
    }

    public static WebDriver getDriver() {
        return ThreadLocal.get();
    }

    public static FluentWait getFluentWait() {
        return fluentWait;
    }

    private static void setFluentWait() {
        fluentWait = new FluentWait(getDriver())
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(FLUENT_WAIT_POLLING_TIME))
                .ignoring(Exception.class);
    }

    public static void quitDriver() {
        logger.info("Quitting driver...");
        Optional.ofNullable(getDriver()).ifPresent(WebDriver ->
                WebDriver.quit()
        );
        logger.info("Driver was quited successfully");
    }
}
