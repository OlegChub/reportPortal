package httpclient.controllers;

import httpclient.client.RequestService;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import static httpclient.endpoints.Endpoints.DASHBOARD_ENDPOINT;
import static httpclient.endpoints.Endpoints.WIDGET_ENDPOINT;

public class WidgetController extends RequestService {

    public CloseableHttpResponse getWidgetById(int widgetId) {
        return getItemById(WIDGET_ENDPOINT, widgetId);
    }

    public CloseableHttpResponse createWidget(String JSONFileName) {
        return postItemWithJson(WIDGET_ENDPOINT, JSONFileName);
    }

    public CloseableHttpResponse updateWidget(int widgetId, String JSONFileName) {
        return updateItem(WIDGET_ENDPOINT, widgetId, JSONFileName);
    }

    public CloseableHttpResponse deleteWidget(int dashboardId, int widgetId) {
        return deleteItem(DASHBOARD_ENDPOINT, dashboardId, widgetId);
    }

}
