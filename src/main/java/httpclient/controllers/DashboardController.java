package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import static httpclient.endpoints.Endpoints.DASHBOARD_ENDPOINT;

public class DashboardController extends BaseRequest {
    private HttpClientBase client;

    public DashboardController(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getDashboardById(int dashboardId) {
        return getItemById(client, DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse createDashboard(String JSONFileName) {
        return postItemWithJson(client, DASHBOARD_ENDPOINT, JSONFileName);
    }

    public CloseableHttpResponse modifyDashboard(int dashboardId, String JSONFileName) {
        return updateItem(client, DASHBOARD_ENDPOINT, dashboardId, JSONFileName);
    }

    public CloseableHttpResponse deleteDashboard(int dashboardId) {
        return deleteItem(client, DASHBOARD_ENDPOINT, dashboardId);
    }
}
