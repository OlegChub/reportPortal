package httpclient.helpers;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;

public class ResponseHandler {

    public static JSONObject getResponseAsJSONObject(CloseableHttpResponse response) throws IOException, ParseException {
        String str = EntityUtils.toString(response.getEntity());
        return new JSONObject(str);
    }

    public static int getIdFromResponse(CloseableHttpResponse response) throws IOException, ParseException {
        return getResponseAsJSONObject(response).getInt("id");
    }
}
