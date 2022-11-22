package ui.pages;

import org.openqa.selenium.By;

public class DemoApiTestsOneLaunchPage extends BasePage {

    private static final By DEMO_API_TESTS_1_LAUNCH_PAGE_TITLE = By.xpath("//*[text()='Demo Api Tests #1']");

    public boolean validateDemoApiTestsOneLaunchPage() {
        return validatePage(DEMO_API_TESTS_1_LAUNCH_PAGE_TITLE);
    }
}
