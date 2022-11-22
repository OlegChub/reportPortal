package ui.pages;

import org.openqa.selenium.By;
import ui.driver.DriverManager;

import static helpers.UserAuthConfig.getPassword;
import static helpers.UserAuthConfig.getUserName;
import static ui.pages.HomePage.HOME_URL;

public class LoginPage extends BasePage {
    private static final String LOGIN_URL = HOME_URL + "/ui/#login";
    private static final By LOGIN_FIELD = By.name("login");
    private static final By PASSWORD_FIELD = By.name("password");
    private static final By LOGIN_BUTTON = By.cssSelector("[type='submit']");

    public LoginPage openLoginPage() {
        DriverManager.getDriver().get(LOGIN_URL);
        return this;
    }

    public LoginPage enterCredentials() {
        findElement(LOGIN_FIELD).sendKeys(getUserName());
        findElement(PASSWORD_FIELD).sendKeys(getPassword());
        return this;
    }

    public LoginPage clickLoginButton() {
        findElement(LOGIN_BUTTON).click();
        return this;
    }

}
