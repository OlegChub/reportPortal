package httpclient.client;

import com.google.common.collect.Lists;
import helpers.UserAuthConfig;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.http.HttpHeaders;

import java.util.List;

public class HttpClientConfig {
    private static String token = UserAuthConfig.getToken();
    private static Header contentTypeHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json");
    private static Header authHeader = new BasicHeader(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", token));

    public static List<Header> getDefaultHeaders() {
        return Lists.newArrayList(contentTypeHeader, authHeader);
    }

}
