package httpclient.controllers;

import httpclient.client.BaseRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import static httpclient.endpoints.Endpoints.DASHBOARD_ENDPOINT;

public class DashboardController extends BaseRequest {

    public CloseableHttpResponse getDashboardById(int dashboardId) {
        return getItemById(DASHBOARD_ENDPOINT, dashboardId);
    }

    public CloseableHttpResponse createDashboard(String JSONFileName) {
        return postItemWithJson(DASHBOARD_ENDPOINT, JSONFileName);
    }

    public CloseableHttpResponse modifyDashboard(int dashboardId, String JSONFileName) {
        return updateItem(DASHBOARD_ENDPOINT, dashboardId, JSONFileName);
    }

    public CloseableHttpResponse deleteDashboard(int dashboardId) {
        return deleteItem(DASHBOARD_ENDPOINT, dashboardId);
    }
}
