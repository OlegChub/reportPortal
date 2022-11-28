package api.request;

import api.BaseRequest;
import org.apache.http.client.methods.HttpPost;

import static helpers.RequestBuilder.buildUrl;
import static httpclient.helpers.JSONHelper.getPreparedJSONFileAsString;

public class PostWidget extends BaseRequest {
    {
        setMethod(HttpPost.METHOD_NAME);
        setUrl(buildUrl("/widget"));
        setBody(getPreparedJSONFileAsString("newWidget"));
    }
}
