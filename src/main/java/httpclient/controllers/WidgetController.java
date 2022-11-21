package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import static httpclient.endpoints.Endpoints.DASHBOARD_ENDPOINT;
import static httpclient.endpoints.Endpoints.WIDGET_ENDPOINT;

public class WidgetController extends BaseRequest {
    private HttpClientBase client;

    public WidgetController(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getWidgetById(int widgetId) {
        return getItemById(client, WIDGET_ENDPOINT, widgetId);
    }

    public CloseableHttpResponse createWidget(String JSONFileName) {
        return postItemWithJson(client, WIDGET_ENDPOINT, JSONFileName);
    }

    public CloseableHttpResponse updateWidget(int widgetId, String JSONFileName) {
        return updateItem(client, WIDGET_ENDPOINT, widgetId, JSONFileName);
    }

    public CloseableHttpResponse deleteWidget(int dashboardId, int widgetId) {
        return deleteItem(client, DASHBOARD_ENDPOINT, dashboardId, widgetId);
    }

}
