package ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import ui.pages.DemoApiTestsOneLaunchPage;
import ui.pages.DemoDashboardPage;
import ui.pages.LaunchPage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UITests extends BaseUITest {
    private LaunchPage launchPage = new LaunchPage();
    private DemoApiTestsOneLaunchPage demoApiTestsOneLaunchPage = new DemoApiTestsOneLaunchPage();
    private DemoDashboardPage demoDashboardPage = new DemoDashboardPage();

    @Test
    void scrollToElementWithJS() {
        assertTrue(launchPage.openLaunchPage().scrollToDefinedElement());
    }

    @Test
    void clickOnElementWithJS() {
        launchPage.openLaunchPage().scrollToDefinedElement();
        launchPage.clickOnDefinedElement();
        assertTrue(demoApiTestsOneLaunchPage.validateDemoApiTestsOneLaunchPage());
    }

    @Test
    void resizeWidget() {
        demoDashboardPage.openDemoDashboardPage();
        int widgetHeight = demoDashboardPage.getWidgetSize().getHeight();
        int widgetWidth = demoDashboardPage.getWidgetSize().getWidth();
        demoDashboardPage.resizeWidget();
        int widgetHeightAfterResize = demoDashboardPage.getWidgetSize().getHeight();
        int widgetWidthAfterResize = demoDashboardPage.getWidgetSize().getWidth();
        demoDashboardPage.refreshPage();

        assertTrue(widgetHeight < widgetHeightAfterResize);
        assertTrue(widgetWidth < widgetWidthAfterResize);
    }

    @Test
    void otherWidgetsMoveWhileResizing() {
        List<WebElement> listOfWidgets = demoDashboardPage.openDemoDashboardPage().getAllWidgets();
        Point locationOfSecondWidget = listOfWidgets.get(1).getLocation();
        int locationY = locationOfSecondWidget.getY();
        demoDashboardPage.resizeWidget();
        List<WebElement> listOfWidgetsAfterResizing = demoDashboardPage.openDemoDashboardPage().getAllWidgets();
        Point locationOfSecondWidgetAfterResizing = listOfWidgetsAfterResizing.get(1).getLocation();
        int locationYAfterResizing = locationOfSecondWidgetAfterResizing.getY();

        assertNotEquals(locationY, locationYAfterResizing);
    }

    @Test
    void contentOfWidgetResizesAsWidget() {
        demoDashboardPage.openDemoDashboardPage();
        Dimension widgetStatisticsDiagramSize = demoDashboardPage.getWidgetStatisticsDiagramSize();
        Dimension widgetLegendContainerSize = demoDashboardPage.getWidgetLegendContainerSize();
        demoDashboardPage.resizeWidget();
        Dimension widgetStatisticsDiagramSizeAfterResize = demoDashboardPage.getWidgetStatisticsDiagramSize();
        Dimension widgetLegendContainerSizeAfterResize = demoDashboardPage.getWidgetLegendContainerSize();

        assertTrue(widgetStatisticsDiagramSize.getHeight() < widgetStatisticsDiagramSizeAfterResize.getHeight());
        assertTrue(widgetStatisticsDiagramSize.getWidth() < widgetStatisticsDiagramSizeAfterResize.getWidth());
        assertTrue(widgetLegendContainerSize.getWidth() < widgetLegendContainerSizeAfterResize.getWidth());
    }

}
