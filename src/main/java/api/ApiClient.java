package api;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpHeaders;

import static helpers.ConfigSetUp.getToken;

public class ApiClient {

    public ValidatableResponse execute(BaseRequest request) {
        addDefaultHeaders(request);
        authorize(request);
        return request.execute();
    }

    private void addDefaultHeaders(BaseRequest request) {
        request.headers.put("Content-Type", "application/json");
    }

    private void authorize(BaseRequest request) {
        request.headers.put(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", getToken()));
    }

}
