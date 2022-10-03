package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.ConfigSetUp.getHost;
import static helpers.ConfigSetUp.getToken;
import static io.restassured.RestAssured.with;

public class RestAssuredSpec {
    static public RequestSpecification requestSpecification =
            with()
                    .baseUri(getHost())
                    .headers("Authorization", "bearer " + getToken())
                    .contentType(ContentType.JSON)
                    .log().all();
    static public ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
