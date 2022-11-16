package httpClientTests;

import httpclient.controllers.WidgetRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;
import static httpclient.helpers.ResponseHandler.getIdFromResponse;
import static httpclient.helpers.ResponseHandler.getResponseAsJSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HttpClientWidgetTests extends HttpClientBaseTest {
    private final String WIDGET_JSON_FILE_NAME = "newWidget";
    private final int DASHBOARD_ID = 46;
    private WidgetRequest widgetRequest = new WidgetRequest(HttpClientBaseTest.client);
    private int newWidgetId;
    private CloseableHttpResponse response;

    @BeforeEach
    protected void testDataPreparation() throws URISyntaxException, IOException, ParseException {
        response = widgetRequest.createWidget(WIDGET_JSON_FILE_NAME);
        newWidgetId = getIdFromResponse(response);
    }

    @Test
    void newWidgetCreated() {
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        assertNotEquals(0, newWidgetId);
    }

    @Test
    void widgetUpdated() throws IOException, URISyntaxException, ParseException {
        JSONObject newWidgetInfo = getResponseAsJSONObject(widgetRequest.getWidgetById(newWidgetId));
        String originalName = getJSONValueByKey("name", newWidgetInfo);
        String originalDescription = getJSONValueByKey("description", newWidgetInfo);
        widgetRequest.updateWidget(newWidgetId, WIDGET_JSON_FILE_NAME);
        JSONObject modifiedWidgetInfo = getResponseAsJSONObject(widgetRequest.getWidgetById(newWidgetId));

        assertNotEquals(originalName, getJSONValueByKey("name", modifiedWidgetInfo));
        assertNotEquals(originalDescription, getJSONValueByKey("description", modifiedWidgetInfo));
    }

    @Test
    void existingWidgetDeleted() throws IOException, URISyntaxException {
        assertEquals(HttpStatus.SC_OK, widgetRequest.deleteWidget(DASHBOARD_ID, newWidgetId).getCode());
    }

    @AfterEach
    protected void cleanUp() throws URISyntaxException, IOException {
        widgetRequest.deleteWidget(DASHBOARD_ID, newWidgetId);
    }
}