package httpclient.client;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.net.URISyntaxException;

import static httpclient.helpers.JSONHelper.getPreparedJSONFileAsString;
import static httpclient.helpers.URIBuilderHelper.buildUri;

public class BaseRequest {
    private HttpUriRequestBase httpRequest;

    public HttpUriRequestBase getHttpRequest() {
        return httpRequest;
    }

    private void setUpHttpRequest(String method, String endpoint) throws URISyntaxException {
        HttpUriRequestBase RequestBase;
        switch (method.toUpperCase()) {
            case (HttpGet.METHOD_NAME):
                RequestBase = new HttpGet(buildUri(endpoint));
                break;

            case (HttpPost.METHOD_NAME):
                RequestBase = new HttpPost(buildUri(endpoint));
                break;

            case (HttpPut.METHOD_NAME):
                RequestBase = new HttpPut(buildUri(endpoint));
                break;

            case (HttpDelete.METHOD_NAME):
                RequestBase = new HttpDelete(buildUri(endpoint));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + method);
        }
        this.httpRequest = RequestBase;
    }

    private void addPostMethodBodyJson(String JSONName) {
        httpRequest.setEntity(new StringEntity(getPreparedJSONFileAsString(JSONName)));
    }

    protected CloseableHttpResponse getItemById(HttpClientBase client, String endpoint, int itemId) throws URISyntaxException, IOException {
        setUpHttpRequest("GET", String.format(endpoint + "/%d", +itemId));
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse postItemWithJson(HttpClientBase client, String endpoint, String JSONName) throws URISyntaxException, IOException {
        setUpHttpRequest("POST", endpoint);
        this.addPostMethodBodyJson(JSONName);
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse updateItem(HttpClientBase client, String endpoint, int itemId, String JSONName) throws URISyntaxException, IOException {
        setUpHttpRequest("PUT", String.format(endpoint + "/%d", +itemId));
        this.addPostMethodBodyJson(JSONName);
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse deleteItem(HttpClientBase client, String endpoint, int... itemId) throws URISyntaxException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);

        for (int i = 0; i < itemId.length; i++)
            stringBuilder.append("/" + itemId[i]);

        setUpHttpRequest("DELETE", stringBuilder.toString());
        return client.execute(this.getHttpRequest());
    }

}
