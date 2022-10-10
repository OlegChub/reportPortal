package api.request;

import api.BaseRequest;

import static helpers.RequestBuilder.urlBuilder;

public class GetDashboard extends BaseRequest {
    {
        method = "GET";
        url = urlBuilder("/dashboard");
    }
}
