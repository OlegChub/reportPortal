package helpers;

import api.ApiClient;
import api.request.PostUserOauthRequest;
import exeptions.FailedToLoginException;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.UserLoginResponse;

public class UserLogin {
    private ApiClient client;
    private static String token;

    public UserLogin(ApiClient client) {
        this.client = client;
    }

    public void login() throws FailedToLoginException {
        ValidatableResponse validatableResponse = client.execute(new PostUserOauthRequest());
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
