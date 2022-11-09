package httpclient.client;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import static httpclient.helpers.URIBuilderHelper.buildUri;

public class BaseRequest {
    private HttpUriRequestBase httpRequest;

    public void setUpHttpRequest(String method, String endpoint) throws URISyntaxException {
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

    public HttpUriRequestBase getHttpRequest() {
        return httpRequest;
    }

    public void addPostMethodBody(JSONObject jsonObject) {
        httpRequest.setEntity(new StringEntity(jsonObject.toString()));
    }

    public CloseableHttpResponse getItemById(HttpClientBase client, String endpoint, int itemId) throws URISyntaxException, IOException {
        setUpHttpRequest("GET", String.format(endpoint + "/%d", +itemId));
        return client.execute(this.getHttpRequest());
    }

    public CloseableHttpResponse postItem(HttpClientBase client, String endpoint, JSONObject json) throws URISyntaxException, IOException {
        setUpHttpRequest("POST", endpoint);
        this.addPostMethodBody(json);
        return client.execute(this.getHttpRequest());
    }

    public CloseableHttpResponse updateWidget(HttpClientBase client, String endpoint, int itemId, JSONObject json) throws URISyntaxException, IOException {
        setUpHttpRequest("PUT", String.format(endpoint + "/%d", +itemId));
        this.addPostMethodBody(json);
        return client.execute(this.getHttpRequest());
    }

    public CloseableHttpResponse deleteItem(HttpClientBase client, String endpoint, int... itemId) throws URISyntaxException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);

        for (int i = 0; i < itemId.length; i++)
            stringBuilder.append("/" + itemId[i]);

        setUpHttpRequest("DELETE", stringBuilder.toString());
        return client.execute(this.getHttpRequest());
    }

}
