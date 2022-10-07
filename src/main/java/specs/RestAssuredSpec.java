package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;

import static helpers.ConfigSetUp.getHost;
import static helpers.ConfigSetUp.getToken;
import static io.restassured.RestAssured.with;

public class RestAssuredSpec {
    static public RequestSpecification requestSpecification =
            with()
                    .baseUri(getHost())
                    .headers(HttpHeaders.AUTHORIZATION, "bearer " + getToken())
                    .contentType(ContentType.JSON)
                    .log().all();
    static public ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(HttpStatus.SC_OK)
            .build();
}
