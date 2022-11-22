package httpclient.client;

import org.apache.hc.client5.http.classic.methods.*;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.io.entity.StringEntity;

import static httpclient.helpers.JSONHelper.getPreparedJSONFileAsString;
import static httpclient.helpers.URIBuilderHelper.buildUri;

public class RequestService {
    private HttpUriRequestBase httpRequest;
    private HttpClientBase client;

    public RequestService() {
        this.client = ClientProvider.getHttpClientBase();
    }

    public HttpUriRequestBase getHttpRequest() {
        return httpRequest;
    }

    private void setUpHttpRequest(String method, String endpoint) {
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

    private void addPostMethodBodyJson(String JSONFileName) {
        httpRequest.setEntity(new StringEntity(getPreparedJSONFileAsString(JSONFileName)));
    }

    protected CloseableHttpResponse getItemById(String endpoint, int itemId) {
        setUpHttpRequest("GET", String.format(endpoint + "/%d", itemId));
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse postItemWithJson(String endpoint, String JSONFileName) {
        setUpHttpRequest("POST", endpoint);
        this.addPostMethodBodyJson(JSONFileName);
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse updateItem(String endpoint, int itemId, String JSONFileName) {
        setUpHttpRequest("PUT", String.format(endpoint + "/%d", itemId));
        this.addPostMethodBodyJson(JSONFileName);
        return client.execute(this.getHttpRequest());
    }

    protected CloseableHttpResponse deleteItem(String endpoint, int... ids) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(endpoint);

        for (int i = 0; i < ids.length; i++) {
            stringBuilder.append("/" + ids[i]);
        }
        setUpHttpRequest("DELETE", stringBuilder.toString());
        return client.execute(this.getHttpRequest());
    }

}
