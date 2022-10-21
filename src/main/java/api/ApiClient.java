package api;

import helpers.UserTokenGenerator;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpHeaders;

import static helpers.UserAuthConfig.getAuthorizationType;

public class ApiClient {

    public ValidatableResponse execute(BaseRequest request) {
        addDefaultHeaders(request);
        authorize(request);
        return request.execute();
    }

    private void addDefaultHeaders(BaseRequest request) {
        request.getHeaders().put("Content-Type", "application/json");
    }

    private void authorize(BaseRequest request) {
        if (UserTokenGenerator.getToken() == null) {
            request.getHeaders().put(HttpHeaders.AUTHORIZATION, getAuthorizationType());
        } else {
            request.getHeaders().put(HttpHeaders.AUTHORIZATION, String.format("Bearer %s", UserTokenGenerator.getToken()));
        }
    }
}
