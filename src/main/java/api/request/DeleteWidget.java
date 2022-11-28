package api.request;

import api.BaseRequest;
import org.apache.http.client.methods.HttpDelete;

import static helpers.RequestBuilder.buildUrl;

public class DeleteWidget extends BaseRequest {
    public DeleteWidget(int dashboardId, int widgetId) {
        setMethod(HttpDelete.METHOD_NAME);
        setUrl(buildUrl("/dashboard/{dashboardId}/{widgetId}"));
        getPathParams().put("dashboardId", String.valueOf(dashboardId));
        getPathParams().put("widgetId", String.valueOf(widgetId));
    }
}
