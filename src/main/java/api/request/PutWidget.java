package api.request;

import api.BaseRequest;
import constants.Constants;
import org.apache.hc.client5.http.classic.methods.HttpPut;

import static helpers.RequestBuilder.buildUrl;
import static httpclient.helpers.StringHelper.replaceSpecificVarWithValue;

public class PutWidget extends BaseRequest {
    public PutWidget(int widgetId) {
        setMethod(HttpPut.METHOD_NAME);
        setUrl(buildUrl(String.format("/dashboard/%d/add", Constants.DASHBOARD_ID)));
        setBody(replaceSpecificVarWithValue("widgetId", widgetId, "addWidgetToDashboard"));
    }
}
