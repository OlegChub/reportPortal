package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class DashboardHandler extends BaseRequest {
    private static final String DASHBOARD_ENDPOINT = "/dashboard";

    private HttpClientBase client;

    public DashboardHandler(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getDashboardById(int dashboardId) throws URISyntaxException, IOException {
        return getItemById(client, DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse createDashboard(JSONObject json) throws URISyntaxException, IOException {
        return postItem(client, DASHBOARD_ENDPOINT, json);
    }

    public CloseableHttpResponse deleteDashboard(int dashboardId) throws URISyntaxException, IOException {
        return deleteItem(client, DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse modifyDashboard(int dashboardId, JSONObject json) throws URISyntaxException, IOException {
        return updateWidget(client, DASHBOARD_ENDPOINT, dashboardId, json);
    }

}
