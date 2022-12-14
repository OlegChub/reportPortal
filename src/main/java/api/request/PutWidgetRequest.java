package api.request;

import api.BaseRequest;
import constants.Constants;
import org.apache.hc.client5.http.classic.methods.HttpPut;

import static helpers.RequestBuilder.buildUrl;
import static httpclient.helpers.StringHelper.replaceSpecificVarWithValue;

public class PutWidgetRequest extends BaseRequest {
    public PutWidgetRequest(int widgetId) {
        setMethod(HttpPut.METHOD_NAME);
        setUrl(buildUrl(String.format("/dashboard/%d/add", Constants.DEMO_DASHBOARD_ID)));
        setBody(replaceSpecificVarWithValue("widgetId", String.valueOf(widgetId), "addWidgetToDashboard"));
    }
}
