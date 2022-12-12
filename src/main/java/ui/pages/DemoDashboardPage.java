package ui.pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import ui.driver.DriverProvider;

import java.util.List;

import static ui.constants.Constants.HOME_URL;
import static ui.helpers.ActionsHelper.resizeSquare;

public class DemoDashboardPage extends BasePage {
    private static final String DEMO_DASHBOARD_PAGE_URL = String.format("%sui/#superadmin_personal/dashboard/%s", HOME_URL, Constants.DEMO_DASHBOARD_ID);
    private static final By DEMO_DASHBOARD_PAGE_TITLE = By.cssSelector("[title='DEMO DASHBOARD']");
    private static final By WIDGET = By.xpath("//div[contains(@class, 'react-grid-item')]");
    private static final By WIDGET_HEADER = By.xpath("//div[contains(@class,'info-block')]");
    private static final By WIDGET_RESIZER = By.xpath("//span[contains(@class, 'react-resizable-handle')]");
    private static final By STATISTICS_DIAGRAM = By.cssSelector("[class='c3-event-rects']");
    private static final By LEGEND_CONTENT_CONTAINER = By.xpath("//div[contains(@class, 'legend__content')]");
    private static final int X_OFFSET = -100;
    private static final int Y_OFFSET = -100;

    public DemoDashboardPage openDemoDashboardPage() {
        DriverProvider.getDriver().get(DEMO_DASHBOARD_PAGE_URL);
        return this;
    }

    public DemoDashboardPage resizeWidgetWithIndex(int widgetIndex) {
        resizeSquare(getAllWidgetResizerElements().get(widgetIndex), X_OFFSET, Y_OFFSET);
        return this;
    }

    public List<WebElement> getAllWidgetResizerElements() {
        return findElements(WIDGET_RESIZER);
    }

    public Dimension getSizeOfWidgetWithIndex(int widgetIndex) {
        return getAllWidgets().get(widgetIndex).getSize();
    }

    public Dimension getSizeOfWidgetLegendContainerWithIndex(int widgetIndex) {
        return findElements(LEGEND_CONTENT_CONTAINER).get(widgetIndex).getSize();
    }

    public Dimension getSizeOfWidgetStatisticsDiagramWithIndex(int widgetIndex) {
        return findElements(STATISTICS_DIAGRAM).get(widgetIndex).getSize();
    }

    public Point getLocationOfWidgetWithIndex(int widgetIndex) {
        return getAllWidgets().get(widgetIndex).getLocation();
    }

    public List<WebElement> getAllWidgets() {
        return findElements(WIDGET);
    }

    public List<WebElement> getAllWidgetsHeaderElements() {
        return findElements(WIDGET_HEADER);
    }
}
