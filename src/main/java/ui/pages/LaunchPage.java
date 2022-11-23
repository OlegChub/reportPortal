package ui.pages;

import org.openqa.selenium.By;
import ui.driver.DriverManager;
import ui.helpers.JSExecutor;

import static ui.pages.HomePage.HOME_URL;

public class LaunchPage extends BasePage {
    private static final String LAUNCH_PAGE = HOME_URL + "ui/#superadmin_personal/launches/all";
    private static final By LAUNCH_PAGE_TITLE = By.xpath("//*[text()='All launches']");
    private static final By ELEMENT_SCROLL_TO = By.xpath("//a[@href='#superadmin_personal/launches/all/1']");

    public LaunchPage openLaunchPage() {
        DriverManager.getDriver().get(LAUNCH_PAGE);
        validatePage(LAUNCH_PAGE_TITLE);
        return this;
    }

    public boolean scrollToDefinedElement() {
        return JSExecutor.scrollToElementUntilItVisible(findElement(ELEMENT_SCROLL_TO));
    }

    public LaunchPage clickOnDefinedElement() {
        JSExecutor.clickOnElement(findElement(ELEMENT_SCROLL_TO));
        return this;
    }

}
