package ui;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import ui.helpers.ActionsHelper;
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
        int widgetHeight = demoDashboardPage.getWidgetSize(1).getHeight();
        int widgetWidth = demoDashboardPage.getWidgetSize(1).getWidth();

        demoDashboardPage.refreshPage();
        demoDashboardPage.moveToWidgetResizerElement(1);
        demoDashboardPage.resizeWidget(1);
        demoDashboardPage.refreshPage();
        int widgetHeightAfterResize = demoDashboardPage.getWidgetSize(1).getHeight();
        int widgetWidthAfterResize = demoDashboardPage.getWidgetSize(1).getWidth();

        assertTrue(widgetHeight > widgetHeightAfterResize);
        assertTrue(widgetWidth > widgetWidthAfterResize);
    }

    @Test
    void otherWidgetsMoveWhileResizing() {
        demoDashboardPage.openDemoDashboardPage();
        int locationY = demoDashboardPage.getWidgetLocation(0).getY();
        demoDashboardPage.refreshPage();
        demoDashboardPage.resizeWidget(1);
        int locationYAfterResizing = demoDashboardPage.getWidgetLocation(0).getY();

        assertNotEquals(locationY, locationYAfterResizing);
    }

    @Test
    void contentOfWidgetResizesAsWidget() {
        demoDashboardPage.openDemoDashboardPage();

        Dimension widgetStatisticsDiagramSize = demoDashboardPage.getWidgetStatisticsDiagramSize(1);
        Dimension widgetLegendContainerSize = demoDashboardPage.getWidgetLegendContainerSize(1);
        demoDashboardPage.refreshPage();
        demoDashboardPage.resizeWidget(1);
        Dimension widgetStatisticsDiagramSizeAfterResize = demoDashboardPage.getWidgetStatisticsDiagramSize(1);
        Dimension widgetLegendContainerSizeAfterResize = demoDashboardPage.getWidgetLegendContainerSize(1);

        assertTrue(widgetStatisticsDiagramSize.getHeight() > widgetStatisticsDiagramSizeAfterResize.getHeight());
        assertTrue(widgetStatisticsDiagramSize.getWidth() > widgetStatisticsDiagramSizeAfterResize.getWidth());
        assertTrue(widgetLegendContainerSize.getWidth() > widgetLegendContainerSizeAfterResize.getWidth());
    }

    @Test
    void replaceWidgetsWIthDragAndDropFunctionality() {
        demoDashboardPage.openDemoDashboardPage();
        demoDashboardPage.refreshPage();

        List<WebElement> WidgetsHeadersList = demoDashboardPage.getAllWidgetsHeaderElements();
        Point firstWidgetLocation = demoDashboardPage.getWidgetLocation(0);
        Point secondWidgetLocation = demoDashboardPage.getWidgetLocation(1);
        ActionsHelper.dragAndDrop(WidgetsHeadersList.get(0), WidgetsHeadersList.get(1));
        Point firstWidgetLocationAfterMoving = demoDashboardPage.getWidgetLocation(0);
        Point secondWidgetLocationAfterMoving = demoDashboardPage.getWidgetLocation(1);

        assertNotEquals(firstWidgetLocation, firstWidgetLocationAfterMoving);
        assertNotEquals(secondWidgetLocation, secondWidgetLocationAfterMoving);
    }

}
