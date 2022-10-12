package helpers;

import io.restassured.response.Response;

import java.lang.reflect.Type;

public class ResponseHandler {
    public static <T> T getResponseAsClass(Class<T> var1, Response response) {
        return response.as((Type) var1);
    }

}
