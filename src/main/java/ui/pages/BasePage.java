package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.driver.DriverManager;

import java.util.List;

public class BasePage {

    public WebElement findElement(By by) {
        DriverManager.getFluentWait().until(ExpectedConditions.presenceOfElementLocated(by));
        return DriverManager.getDriver().findElement(by);
    }

    public List<WebElement> findElements(By by) {
        DriverManager.getFluentWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return DriverManager.getDriver().findElements(by);
    }

    public boolean validatePage(By by) {
        return !findElements(by).isEmpty();
    }

    public void refreshPage() {
        DriverManager.getDriver().navigate().refresh();
    }
}
