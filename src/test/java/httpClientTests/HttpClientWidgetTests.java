package httpClientTests;

import httpclient.controllers.WidgetController;
import httpclient.log.HttpClientLogger;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;
import static httpclient.helpers.ResponseHandler.getIdFromResponse;
import static httpclient.helpers.ResponseHandler.getResponseAsJSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Tag("httpClientTests")
public class HttpClientWidgetTests extends HttpClientBaseTest {
    private final String WIDGET_JSON_FILE_NAME = "newWidget";
    private final int DASHBOARD_ID = 46;
    private WidgetController widgetController = new WidgetController();
    private int newWidgetId;
    private CloseableHttpResponse response;

    @BeforeEach
    protected void prepareTestData() {
        HttpClientLogger.logger.info("New widget creating ...");
        response = widgetController.createWidget(WIDGET_JSON_FILE_NAME);
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        HttpClientLogger.logger.info("Widget successfully created");
        newWidgetId = getIdFromResponse(response);
    }

    @Test
    void createNewWidget() {
        assertNotEquals(0, newWidgetId);
    }

    @Test
    void updateWidget() {
        JSONObject newWidgetInfo = getResponseAsJSONObject(widgetController.getWidgetById(newWidgetId));
        String originalName = getJSONValueByKey("name", newWidgetInfo);
        String originalDescription = getJSONValueByKey("description", newWidgetInfo);
        widgetController.updateWidget(newWidgetId, WIDGET_JSON_FILE_NAME);
        JSONObject modifiedWidgetInfo = getResponseAsJSONObject(widgetController.getWidgetById(newWidgetId));

        assertNotEquals(originalName, getJSONValueByKey("name", modifiedWidgetInfo));
        assertNotEquals(originalDescription, getJSONValueByKey("description", modifiedWidgetInfo));
    }

    @Test
    void deleteWidget() {
        assertEquals(HttpStatus.SC_OK, widgetController.deleteWidget(DASHBOARD_ID, newWidgetId).getCode());
    }

    @AfterEach
    protected void cleanUp() {
        HttpClientLogger.logger.info("Deleting testing data ...");
        widgetController.deleteWidget(DASHBOARD_ID, newWidgetId);
        HttpClientLogger.logger.info("Testing data successfully deleted");
    }
}