package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

public class DashboardRequest extends BaseRequest {
    private static final String DASHBOARD_ENDPOINT = "/dashboard";
    private HttpClientBase client;

    public DashboardRequest(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getDashboardById(int dashboardId) {
        return getItemById(client, DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse createDashboard(String JSONName) {
        return postItemWithJson(client, DASHBOARD_ENDPOINT, JSONName);
    }

    public CloseableHttpResponse modifyDashboard(int dashboardId, String JSONName) {
        return updateItem(client, DASHBOARD_ENDPOINT, dashboardId, JSONName);
    }

    public CloseableHttpResponse deleteDashboard(int dashboardId) {
        return deleteItem(client, DASHBOARD_ENDPOINT, dashboardId);
    }
}
