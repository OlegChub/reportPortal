package httpclient.helpers;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ResponseHandler {

    public static JSONObject getResponseAsJSONObject(CloseableHttpResponse response) {
        String responseAsString;
        try {
            responseAsString = EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new JSONObject(responseAsString);
    }

    public static int getIdFromResponse(CloseableHttpResponse response) {
        return getResponseAsJSONObject(response).getInt("id");
    }
}
