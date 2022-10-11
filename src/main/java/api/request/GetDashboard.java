package api.request;

import api.BaseRequest;

import static helpers.RequestBuilder.buildUrl;

public class GetDashboard extends BaseRequest {
    {
        method = "GET";
        url = buildUrl("/dashboard");
    }
}
