package httpclient.helpers;

import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

import static helpers.ConfigSetUp.*;

public class URIBuilderHelper {

    private static URIBuilder getUriBuilder(String endpoint) {
        URIBuilder builder = new URIBuilder();
        builder
                .setScheme(getScheme())
                .setHost(getLocalhost())
                .setPort(getPort())
                .setPath("/api/v1/superadmin_personal" + endpoint);
        return builder;
    }

    public static URI buildUri(String endpoint) throws URISyntaxException {
        return getUriBuilder(endpoint)
                .build();
    }

}
