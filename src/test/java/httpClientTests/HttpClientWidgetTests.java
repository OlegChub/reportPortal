package httpClientTests;

import httpclient.controllers.WidgetHandler;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static httpclient.helpers.JSONTestData.getJSONValueByKey;
import static httpclient.helpers.JSONTestData.getNewWidgetJSON;
import static httpclient.helpers.ResponseHandler.getIdFromResponse;
import static httpclient.helpers.ResponseHandler.getResponseAsJSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HttpClientWidgetTests extends HttpClientBaseTest {
    private WidgetHandler widgetHandler = new WidgetHandler(HttpClientBaseTest.client);
    private final int DASHBOARD_ID = 46;
    private int newWidgetId;
    private CloseableHttpResponse response;

    @BeforeEach
    protected void testDataPreparation() throws URISyntaxException, IOException, ParseException {
        response = widgetHandler.createWidget(getNewWidgetJSON());
        newWidgetId = getIdFromResponse(response);
    }

    @Test
    void newWidgetCreated() {
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        assertNotEquals(0, newWidgetId);
    }

    @Test
    void widgetUpdated() throws IOException, URISyntaxException, ParseException {
        JSONObject newWidgetInfo = getResponseAsJSONObject(widgetHandler.getWidgetById(newWidgetId));
        String originalName = getJSONValueByKey("name", newWidgetInfo);
        String originalDescription = getJSONValueByKey("description", newWidgetInfo);

        JSONObject modifiedJSON = getNewWidgetJSON();
        String newWidgetName = getJSONValueByKey("name", modifiedJSON);
        String newWidgetDescription = getJSONValueByKey("description", modifiedJSON);

        int StatusCodeAfterUpdate = widgetHandler.updateWidget(newWidgetId, modifiedJSON).getCode();

        JSONObject ModifiedWidgetInfo = getResponseAsJSONObject(widgetHandler.getWidgetById(newWidgetId));

        assertEquals(HttpStatus.SC_OK, StatusCodeAfterUpdate);
        assertEquals(newWidgetName, getJSONValueByKey("name", ModifiedWidgetInfo));
        assertNotEquals(newWidgetName, originalName);
        assertEquals(newWidgetDescription, getJSONValueByKey("description", ModifiedWidgetInfo));
        assertNotEquals(newWidgetDescription, originalDescription);
    }

    @Test
    void existingWidgetDeleted() throws IOException, URISyntaxException {
        assertEquals(HttpStatus.SC_OK, widgetHandler.deleteWidget(DASHBOARD_ID, newWidgetId).getCode());
    }

    @AfterEach
    protected void cleanUp() throws URISyntaxException, IOException {
        widgetHandler.deleteWidget(DASHBOARD_ID, newWidgetId);
    }
}