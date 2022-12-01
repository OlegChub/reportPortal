package ui.driverConfig;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.openqa.selenium.chrome.ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;
import static org.openqa.selenium.edge.EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY;

public class DriverProperties {
    private final static BrowserConfig config = ConfigFactory.create(BrowserConfig.class, System.getProperties());
    private final static String CHROME_DRIVER_PATH = "src\\main\\resources\\driver\\chromedriver.exe";
    private final static String EDGE_DRIVER_PATH = "src\\main\\resources\\driver\\msedgedriver.exe";

    private DriverProperties() {
    }

    public static String getBrowserName() {
        return config.browser();
    }

    public static WebDriver setUpDriverWithProperties() {
        WebDriver driver;
        switch (getBrowserName().toLowerCase()) {
            case ("chrome"):
                System.setProperty(CHROME_DRIVER_EXE_PROPERTY, CHROME_DRIVER_PATH);
                return driver = new ChromeDriver();
            case ("edge"):
                System.setProperty(EDGE_DRIVER_EXE_PROPERTY, EDGE_DRIVER_PATH);
                return driver = new EdgeDriver();
            default:
                throw new IllegalStateException("Unexpected browser name: " + getBrowserName());
        }
    }

}
