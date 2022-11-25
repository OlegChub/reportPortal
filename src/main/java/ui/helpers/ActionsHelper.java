package ui.helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui.driver.DriverManager;

public class ActionsHelper {
    public static void dragAndDrop(WebElement source, WebElement target) {
        new Actions(DriverManager.getDriver()).dragAndDrop(source, target).build().perform();
    }

    public static void dragAndDropByCoordinates(WebElement source, int xOffset, int yOffset) {
        new Actions(DriverManager.getDriver()).dragAndDropBy(source, xOffset, yOffset).build().perform();
    }

    public static void resizeSquare(WebElement element, int xOffset, int yOffset) {
        new Actions(DriverManager.getDriver()).clickAndHold(element).moveByOffset(xOffset, yOffset).release().build().perform();
    }
}
