package helpers;

import api.ApiClient;
import api.request.PostUserOauth;
import exeptions.FailedToLoginException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.UserLoginResponse;

public class UserTokenGenerator {
    private ApiClient client;
    private static String token;

    public UserTokenGenerator(ApiClient client) {
        this.client = client;
    }

    public void setToken() throws FailedToLoginException {
        ValidatableResponse validatableResponse = client.execute(new PostUserOauth());
        Response response = validatableResponse.extract().response();
        if (response.statusCode() != 200) {
            throw new FailedToLoginException("Failed to login");
        }
        this.token = ResponseHandler.getResponseAsClass(UserLoginResponse.class, response).getToken();
    }

    public static String getToken() {
        return token;
    }
}
