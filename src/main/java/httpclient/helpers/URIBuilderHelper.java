package httpclient.helpers;

import helpers.ConfigSetUp;
import org.apache.hc.core5.net.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class URIBuilderHelper {

    public static URI buildUri(String endpoint) {
        URIBuilder builder = new URIBuilder();
        builder.setScheme(ConfigSetUp.getScheme())
                .setHost(ConfigSetUp.getLocalhost())
                .setPort(ConfigSetUp.getPort())
                .setPath("/api/v1/superadmin_personal" + endpoint);
        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

}
