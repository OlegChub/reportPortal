package ui.driver.wait;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import ui.driver.DriverManager;

import java.time.Duration;

public class CustomFluentWait {
    private final static int FLUENT_WAIT_TIMEOUT = 10;
    private final static int FLUENT_WAIT_POLLING_TIME = 1;

    private static FluentWait customFluentWait;

    public static FluentWait getCustomFluentWait() {
        return customFluentWait;
    }

    public static FluentWait setCustomFluentWait() {
        return customFluentWait = new FluentWait(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT))
                .pollingEvery(Duration.ofSeconds(FLUENT_WAIT_POLLING_TIME))
                .ignoring(NoSuchElementException.class);
    }

}
