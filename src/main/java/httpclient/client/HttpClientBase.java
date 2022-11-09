package httpclient.client;

import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

import static httpclient.client.HttpClientConfig.getDefaultHeaders;

public class HttpClientBase {
    private CloseableHttpClient httpClient;

    public HttpClientBase() {
        this.httpClient = HttpClients.custom().setDefaultHeaders(getDefaultHeaders()).build();
    }

    public CloseableHttpResponse execute(HttpUriRequestBase requestBase) throws IOException {
        return this.httpClient.execute(requestBase);
    }
}
