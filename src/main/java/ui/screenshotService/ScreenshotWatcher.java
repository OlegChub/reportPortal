package ui.screenshotService;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import ui.driver.DriverProvider;

import static ui.screenshotService.ScreenshotHelper.captureScreenshot;

public class ScreenshotWatcher implements TestWatcher {
    private final static String PATH_TO_SCREENSHOTS_STORAGE = "target/surefire-reports/screenshots";

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        captureScreenshot(DriverProvider.getDriver(), context.getDisplayName(), PATH_TO_SCREENSHOTS_STORAGE);
    }
}
