package ui.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.driver.DriverManager;
import ui.driver.wait.CustomFluentWait;

public class JSExecutor {

    public static boolean scrollToElementUntilItVisible(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView();", CustomFluentWait.getCustomFluentWait().until(ExpectedConditions.visibilityOf(element)));
        return true;
    }

    public static void clickOnElement(WebElement element) {
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", element);
    }

}
