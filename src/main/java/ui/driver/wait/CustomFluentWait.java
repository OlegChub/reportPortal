package ui.driver.wait;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.FluentWait;
import ui.driver.DriverProvider;

import java.time.Duration;

public class CustomFluentWait {
    private final static int FLUENT_WAIT_TIMEOUT = 10;
    private final static int FLUENT_WAIT_POLLING_TIME = 1;

    private static FluentWait customFluentWait;

    private CustomFluentWait() {
    }

    public static FluentWait getCustomFluentWait() {
        if (customFluentWait == null) {
            customFluentWait = new FluentWait(DriverProvider.getDriver())
                    .withTimeout(Duration.ofSeconds(FLUENT_WAIT_TIMEOUT))
                    .pollingEvery(Duration.ofSeconds(FLUENT_WAIT_POLLING_TIME))
                    .ignoring(NoSuchElementException.class);
        }
        return customFluentWait;
    }

}
