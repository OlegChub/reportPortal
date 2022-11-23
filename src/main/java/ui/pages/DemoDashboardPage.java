package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import ui.driver.DriverManager;

import java.util.List;

import static ui.helpers.ActionsHelper.resizeSquare;
import static ui.pages.HomePage.HOME_URL;

public class DemoDashboardPage extends BasePage {
    private static final String DEMO_DASHBOARD_PAGE_URL = HOME_URL + "ui/#superadmin_personal/dashboard/46";
    private static final By DEMO_DASHBOARD_PAGE_TITLE = By.cssSelector("[title='DEMO DASHBOARD']");
    private static final By WIDGET = By.xpath("//div[contains(@class, 'react-grid-item')]");
    private static final By WIDGET_RESIZER = By.xpath("//span[contains(@class, 'react-resizable-handle')]");
    private static final By STATISTICS_DIAGRAM = By.cssSelector("[class='c3-chart-lines']");
    private static final By LEGEND_CONTENT_CONTAINER = By.xpath("//div[contains(@class, 'legend__content')]");

    public DemoDashboardPage openDemoDashboardPage() {
        DriverManager.getDriver().get(DEMO_DASHBOARD_PAGE_URL);
        validatePage(DEMO_DASHBOARD_PAGE_TITLE);
        return this;
    }

    public DemoDashboardPage resizeWidget() {
        resizeSquare(WIDGET_RESIZER, 150, 100);
        return this;
    }

    public Dimension getWidgetSize() {
        return findElement(WIDGET).getSize();
    }

    public Dimension getWidgetLegendContainerSize() {
        return findElement(LEGEND_CONTENT_CONTAINER).getSize();
    }

    public Dimension getWidgetStatisticsDiagramSize() {
        return findElement(STATISTICS_DIAGRAM).getSize();
    }

    public List<WebElement> getAllWidgets() {
        return findElements(WIDGET);
    }

}
