package api.request;

import api.BaseRequest;
import org.apache.http.client.methods.HttpGet;

import static helpers.RequestBuilder.buildUrl;

public class GetLaunchInfoRequest extends BaseRequest {
    public GetLaunchInfoRequest(int launchId) {
        super();
        setMethod(HttpGet.METHOD_NAME);
        setUrl(buildUrl("/launch/{launchId}"));
        getPathParams().put("launchId", String.valueOf(launchId));

    }
}
