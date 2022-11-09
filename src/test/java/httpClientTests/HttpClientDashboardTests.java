package httpClientTests;

import httpclient.controllers.DashboardHandler;
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
import static httpclient.helpers.JSONTestData.getNewDashboardJSON;
import static httpclient.helpers.ResponseHandler.getIdFromResponse;
import static httpclient.helpers.ResponseHandler.getResponseAsJSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HttpClientDashboardTests extends HttpClientBaseTest {
    private DashboardHandler dashboardHandler = new DashboardHandler(HttpClientBaseTest.client);
    private CloseableHttpResponse response;
    private int newDashboardId;

    @BeforeEach
    protected void testDataPreparation() throws URISyntaxException, IOException, ParseException {
        response = dashboardHandler.createDashboard(getNewDashboardJSON());
        newDashboardId = getIdFromResponse(response);
    }

    @Test
    void newDashboardCreated() {
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        assertNotEquals(0, newDashboardId);
    }

    @Test
    void createdDashboardModified() throws IOException, URISyntaxException, ParseException {
        JSONObject newDashboardInfo = getResponseAsJSONObject(dashboardHandler.getDashboardById(newDashboardId));
        String originalName = getJSONValueByKey("name", newDashboardInfo);
        String originalDescription = getJSONValueByKey("description", newDashboardInfo);

        JSONObject modifiedJSON = getNewDashboardJSON();
        String newDashboardName = getJSONValueByKey("name", modifiedJSON);
        String newDashboardDescription = getJSONValueByKey("description", modifiedJSON);

        dashboardHandler.modifyDashboard(newDashboardId, modifiedJSON);

        JSONObject ModifiedDashboardInfo = getResponseAsJSONObject(dashboardHandler.getDashboardById(newDashboardId));

        assertEquals(newDashboardName, getJSONValueByKey("name", ModifiedDashboardInfo));
        assertNotEquals(newDashboardName, originalName);
        assertEquals(newDashboardDescription, getJSONValueByKey("description", ModifiedDashboardInfo));
        assertNotEquals(newDashboardDescription, originalDescription);
    }

    @Test
    void existingDashboardDeleted() throws IOException, URISyntaxException {
        assertEquals(HttpStatus.SC_OK, dashboardHandler.deleteDashboard(newDashboardId).getCode());
    }

    @AfterEach
    protected void cleanUp() throws URISyntaxException, IOException {
        dashboardHandler.deleteDashboard(newDashboardId);
    }
}
