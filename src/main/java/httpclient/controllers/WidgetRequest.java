package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

import java.io.IOException;
import java.net.URISyntaxException;

public class WidgetRequest extends BaseRequest {
    private static final String WIDGET_ENDPOINT = "/widget";
    private HttpClientBase client;

    public WidgetRequest(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getWidgetById(int widgetId) throws URISyntaxException, IOException {
        return getItemById(client, WIDGET_ENDPOINT, widgetId);
    }

    public CloseableHttpResponse createWidget(String JSONName) throws URISyntaxException, IOException {
        return postItemWithJson(client, WIDGET_ENDPOINT, JSONName);
    }

    public CloseableHttpResponse updateWidget(int widgetId, String JSONName) throws URISyntaxException, IOException {
        return updateItem(client, WIDGET_ENDPOINT, widgetId, JSONName);
    }

    public CloseableHttpResponse deleteWidget(int dashboardId, int widgetId) throws URISyntaxException, IOException {
        return deleteItem(client, "/dashboard", dashboardId, widgetId);
    }

}
