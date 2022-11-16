package httpClientTests;

import httpclient.controllers.DashboardRequest;
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
    private DashboardRequest dashboardRequest = new DashboardRequest(HttpClientBaseTest.client);
    private CloseableHttpResponse response;
    private int newDashboardId;

    @BeforeEach
    protected void testDataPreparation() {
        response = dashboardRequest.createDashboard(DASHBOARD_JSON_FILE_NAME);
        newDashboardId = getIdFromResponse(response);
    }

    @Test
    void newDashboardCreated() {
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        assertNotEquals(0, newDashboardId);
    }

    @Test
    void createdDashboardModified() {
        JSONObject newDashboardInfo = getResponseAsJSONObject(dashboardRequest.getDashboardById(newDashboardId));
        String originalName = getJSONValueByKey("name", newDashboardInfo);
        String originalDescription = getJSONValueByKey("description", newDashboardInfo);
        dashboardRequest.modifyDashboard(newDashboardId, DASHBOARD_JSON_FILE_NAME);
        JSONObject modifiedDashboardInfo = getResponseAsJSONObject(dashboardRequest.getDashboardById(newDashboardId));

        assertNotEquals(originalName, getJSONValueByKey("name", modifiedDashboardInfo));
        assertNotEquals(originalDescription, getJSONValueByKey("description", modifiedDashboardInfo));
    }

    @Test
    void existingDashboardDeleted() {
        assertEquals(HttpStatus.SC_OK, dashboardRequest.deleteDashboard(newDashboardId).getCode());
    }

    @AfterEach
    protected void cleanUp() {
        dashboardRequest.deleteDashboard(newDashboardId);
    }
}
