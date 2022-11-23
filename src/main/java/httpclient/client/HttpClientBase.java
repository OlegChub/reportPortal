package httpclient.client;

import exeptions.ProceedFailedException;
import httpclient.log.HttpClientLogger;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;

public class HttpClientBase {
    private CloseableHttpClient httpClient;

    public HttpClientBase() {
        this.httpClient = HttpClients.custom().setDefaultHeaders(HttpClientConfig.getDefaultHeaders()).build();
    }

    public CloseableHttpResponse execute(HttpUriRequestBase requestBase) {
        try {
            return this.httpClient.execute(requestBase);
        } catch (IOException e) {
            HttpClientLogger.logger.error("Failed to execute the request");
            throw new ProceedFailedException(e.getMessage());
        }
    }
}
