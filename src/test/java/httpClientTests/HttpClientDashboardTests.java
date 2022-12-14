package httpClientTests;

import httpclient.controllers.DashboardController;
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
public class HttpClientDashboardTests extends HttpClientBaseTest {
    private final String DASHBOARD_JSON_FILE_NAME = "newDashboard";
    private DashboardController dashboardController = new DashboardController();
    private CloseableHttpResponse response;
    private int newDashboardId;

    @BeforeEach
    protected void prepareTestData() {
        HttpClientLogger.logger.info("New dashboard creating ...");
        response = dashboardController.createDashboard(DASHBOARD_JSON_FILE_NAME);
        assertEquals(HttpStatus.SC_CREATED, response.getCode());
        HttpClientLogger.logger.info("Dashboard successfully created");
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
        HttpClientLogger.logger.info("Deleting testing data ...");
        dashboardController.deleteDashboard(newDashboardId);
        HttpClientLogger.logger.info("Testing data successfully deleted");
    }
}
