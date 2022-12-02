package api.request;

import api.BaseRequest;
import org.apache.http.client.methods.HttpGet;

import static helpers.RequestBuilder.buildUrl;

public class GetDashboardRequest extends BaseRequest {
    {
        setMethod(HttpGet.METHOD_NAME);
        setUrl(buildUrl("/dashboard"));
    }
}
