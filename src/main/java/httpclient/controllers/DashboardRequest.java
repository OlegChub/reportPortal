package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class DashboardRequest extends BaseRequest {
    private static final String DASHBOARD_ENDPOINT = "/dashboard";
    private HttpClientBase client;

    public DashboardRequest(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getDashboardById(int dashboardId) throws URISyntaxException, IOException {
        return getItemById(client, DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse createDashboard(String JSONName) throws URISyntaxException, IOException {
        return postItemWithJson(client, DASHBOARD_ENDPOINT, JSONName);
    }

    public CloseableHttpResponse modifyDashboard(int dashboardId, String JSONName) throws URISyntaxException, IOException {
        return updateItem(client, DASHBOARD_ENDPOINT, dashboardId, JSONName);
    }

    public CloseableHttpResponse deleteDashboard(int dashboardId) throws URISyntaxException, IOException {
        return deleteItem(client, DASHBOARD_ENDPOINT, dashboardId);
    }
}
