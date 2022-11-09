package httpclient.controllers;

import httpclient.client.BaseRequest;
import httpclient.client.HttpClientBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

public class WidgetHandler extends BaseRequest {
    private static final String WIDGET_ENDPOINT = "/widget";

    private HttpClientBase client;

    public WidgetHandler(HttpClientBase client) {
        this.client = client;
    }

    public CloseableHttpResponse getWidgetById(int widgetId) throws URISyntaxException, IOException {
        return getItemById(client, WIDGET_ENDPOINT, widgetId);
    }

    public CloseableHttpResponse createWidget(JSONObject json) throws URISyntaxException, IOException {
        return postItem(client, WIDGET_ENDPOINT, json);
    }

    public CloseableHttpResponse deleteWidget(int dashboardId, int widgetId) throws URISyntaxException, IOException {
        return deleteItem(client, "/dashboard", dashboardId, widgetId);
    }

    public CloseableHttpResponse updateWidget(int widgetId, JSONObject json) throws URISyntaxException, IOException {
        return updateWidget(client, WIDGET_ENDPOINT, widgetId, json);
    }

}
