package httpclient.client;

import com.google.common.collect.Lists;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.http.HttpHeaders;

import java.util.List;

import static helpers.UserAuthConfig.getToken;

public class HttpClientConfig {
    private static String token = getToken();
    static List<Header> headers;
    private static Header contentTypeHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    private static Header authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

    public static List<Header> getDefaultHeaders() {
        return headers = Lists.newArrayList(contentTypeHeader, authHeader);
    }

}
