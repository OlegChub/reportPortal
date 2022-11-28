package ui.pages;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import ui.driver.DriverManager;

import java.util.List;

import static ui.helpers.ActionsHelper.resizeSquare;
import static ui.pages.HomePage.HOME_URL;

public class DemoDashboardPage extends BasePage {
    private static final String DEMO_DASHBOARD_PAGE_URL = HOME_URL + String.format("ui/#superadmin_personal/dashboard/%s", Constants.DASHBOARD_ID);
    private static final By DEMO_DASHBOARD_PAGE_TITLE = By.cssSelector("[title='DEMO DASHBOARD']");
    private static final By WIDGET = By.xpath("//div[contains(@class, 'react-grid-item')]");
    private static final By WIDGET_HEADER = By.xpath("//div[contains(@class,'info-block')]");
    private static final By WIDGET_RESIZER = By.xpath("//span[contains(@class, 'react-resizable-handle')]");
    private static final By STATISTICS_DIAGRAM = By.cssSelector("[class='c3-event-rects']");
    private static final By LEGEND_CONTENT_CONTAINER = By.xpath("//div[contains(@class, 'legend__content')]");

    public DemoDashboardPage openDemoDashboardPage() {
        DriverManager.getDriver().get(DEMO_DASHBOARD_PAGE_URL);
        validatePage(DEMO_DASHBOARD_PAGE_TITLE);
        return this;
    }

    public DemoDashboardPage resizeWidget(int widgetIndex) {
        resizeSquare(getAllWidgetResizerElements().get(widgetIndex), -100, -100);
        return this;
    }

    public List<WebElement> getAllWidgetResizerElements() {
        return findElements(WIDGET_RESIZER);
    }

    public Dimension getWidgetSize(int widgetIndex) {
        return getAllWidgets().get(widgetIndex).getSize();
    }

    public Dimension getWidgetLegendContainerSize(int widgetIndex) {
        return findElements(LEGEND_CONTENT_CONTAINER).get(widgetIndex).getSize();
    }

    public Dimension getWidgetStatisticsDiagramSize(int widgetIndex) {
        return findElements(STATISTICS_DIAGRAM).get(widgetIndex).getSize();
    }

    public Point getWidgetLocation(int widgetIndex) {
        return getAllWidgets().get(widgetIndex).getLocation();
    }

    public List<WebElement> getAllWidgets() {
        return findElements(WIDGET);
    }

    public List<WebElement> getAllWidgetsHeaderElements() {
        return findElements(WIDGET_HEADER);
    }
}
