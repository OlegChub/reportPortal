package httpClientTests;

import httpclient.controllers.DashboardController;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;
import static httpclient.helpers.ResponseHandler.getIdFromResponse;
import static httpclient.helpers.ResponseHandler.getResponseAsJSONObject;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HttpClientDashboardTests extends HttpClientBaseTest {
    private final String DASHBOARD_JSON_FILE_NAME = "newDashboard";
    private DashboardController dashboardController = new DashboardController(client);
    private CloseableHttpResponse response;
    private int newDashboardId;

    @BeforeEach
    protected void prepareTestData() {
        response = dashboardController.createDashboard(DASHBOARD_JSON_FILE_NAME);
        newDashboardId = getIdFromResponse(response);
    }

    @Test
    void createNewDashboard() {
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        assertNotEquals(0, newDashboardId);
    }

    @Test
    void modifyDashboard() {
        JSONObject newDashboardInfo = getResponseAsJSONObject(dashboardController.getDashboardById(newDashboardId));
        String originalName = getJSONValueByKey("name", newDashboardInfo);
        String originalDescription = getJSONValueByKey("description", newDashboardInfo);
        dashboardController.modifyDashboard(newDashboardId, DASHBOARD_JSON_FILE_NAME);
        JSONObject modifiedDashboardInfo = getResponseAsJSONObject(dashboardController.getDashboardById(newDashboardId));

        assertNotEquals(originalName, getJSONValueByKey("name", modifiedDashboardInfo));
        assertNotEquals(originalDescription, getJSONValueByKey("description", modifiedDashboardInfo));
    }

    @Test
    void deleteDashboard() {
        assertEquals(HttpStatus.SC_OK, dashboardController.deleteDashboard(newDashboardId).getCode());
    }

    @AfterEach
    protected void cleanUp() {
        dashboardController.deleteDashboard(newDashboardId);
    }
}
