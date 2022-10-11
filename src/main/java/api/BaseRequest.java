package api;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static helpers.ConfigSetUp.getHost;
import static io.restassured.RestAssured.given;

public class BaseRequest {
    private String method;
    private String url;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> pathParams = new HashMap<>();
    private Map<String, String> queryParams = new HashMap<>();

    private Object body;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getPathParams() {
        return pathParams;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public ValidatableResponse execute() {
        RequestSpecification request =
                given().log().all()
                        .baseUri(getHost())
                        .headers(getHeaders())
                        .pathParams(getPathParams())
                        .queryParams(getQueryParams());

        if (getBody() != null) {
            request.body(getBody());
        }

        return request
                .when()
                .request(getMethod(), getUrl())
                .then().log().all();
    }
}

