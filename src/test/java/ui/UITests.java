package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private final LaunchPage launchPage = new LaunchPage();
    private final DemoDashboardPage demoDashboardPage = new DemoDashboardPage();
    private static final Logger LOGGER = LogManager.getLogger();

    @Test
    void scrollToElementWithJS() {
        assertTrue(launchPage.openLaunchPage().scrollToDefinedElement());
    }

    @Test
    void clickOnElementWithJS() {
        DemoApiTestsOneLaunchPage demoApiTestsOneLaunchPage = new DemoApiTestsOneLaunchPage();
        launchPage.openLaunchPage().scrollToDefinedElement();
        launchPage.clickOnDefinedElement();
        assertTrue(demoApiTestsOneLaunchPage.validateDemoApiTestsOneLaunchPage());
    }

    @Test
    void resizeWidget() {
        LOGGER.info("Preparing test data ...");
        widgetId1 = apiSteps.createAndAddWidget(widget);
        widgetId2 = apiSteps.createAndAddWidget(widget);
        LOGGER.info("Test data successfully prepared");

        demoDashboardPage.openDemoDashboardPage();
        int widgetHeight = demoDashboardPage.getWidgetSize(0).getHeight();
        int widgetWidth = demoDashboardPage.getWidgetSize(0).getWidth();
        demoDashboardPage.resizeWidget(0);
        demoDashboardPage.refreshPage();
        int widgetHeightAfterResize = demoDashboardPage.getWidgetSize(0).getHeight();
        int widgetWidthAfterResize = demoDashboardPage.getWidgetSize(0).getWidth();

        assertTrue(widgetHeight > widgetHeightAfterResize);
        assertTrue(widgetWidth > widgetWidthAfterResize);
    }

    @Test
    void otherWidgetsMoveWhileResizing() {
        LOGGER.info("Preparing test data ...");
        widgetId1 = apiSteps.createAndAddWidget(widget);
        widgetId2 = apiSteps.createAndAddWidget(widget);
        LOGGER.info("Test data successfully prepared");

        demoDashboardPage.openDemoDashboardPage();
        int locationY = demoDashboardPage.getWidgetLocation(0).getY();
        demoDashboardPage.refreshPage();
        demoDashboardPage.resizeWidget(1);
        int locationYAfterResizing = demoDashboardPage.getWidgetLocation(0).getY();

        assertNotEquals(locationY, locationYAfterResizing);
    }

    @Test
    void contentOfWidgetResizesAsWidget() {
        LOGGER.info("Preparing test data ...");
        widgetId1 = apiSteps.createAndAddWidget(widget);
        widgetId2 = apiSteps.createAndAddWidget(widget);
        LOGGER.info("Test data successfully prepared");

        demoDashboardPage.openDemoDashboardPage();

        Dimension widgetStatisticsDiagramSize = demoDashboardPage.getWidgetStatisticsDiagramSize(1);
        Dimension widgetLegendContainerSize = demoDashboardPage.getWidgetLegendContainerSize(1);
        demoDashboardPage.resizeWidget(1);
        Dimension widgetStatisticsDiagramSizeAfterResize = demoDashboardPage.getWidgetStatisticsDiagramSize(1);
        Dimension widgetLegendContainerSizeAfterResize = demoDashboardPage.getWidgetLegendContainerSize(1);

        assertTrue(widgetStatisticsDiagramSize.getHeight() > widgetStatisticsDiagramSizeAfterResize.getHeight());
        assertTrue(widgetStatisticsDiagramSize.getWidth() > widgetStatisticsDiagramSizeAfterResize.getWidth());
        assertTrue(widgetLegendContainerSize.getWidth() > widgetLegendContainerSizeAfterResize.getWidth());
    }

    @Test
    void replaceWidgetsWithDragAndDropFunctionality() {
        LOGGER.info("Preparing test data ...");
        widgetId1 = apiSteps.createAndAddWidget(widget);
        widgetId2 = apiSteps.createAndAddWidget(widget);
        LOGGER.info("Test data successfully prepared");

        demoDashboardPage.openDemoDashboardPage();

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
