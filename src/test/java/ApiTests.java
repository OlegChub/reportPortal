import models.Dashboard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.ConfigSetUp.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RestAssuredSpec.requestSpecification;
import static specs.RestAssuredSpec.resSpec;

public class ApiTests {

    @Test
    @DisplayName("Get all permitted dashboard resources for specified project")
    public void getAllPermittedDashboard() {

        Dashboard response= given(requestSpecification)
                .contentType("application/json; charset=UTF-8")
                .when()
                .get("api/v1/"+getProjectName()+"/dashboard/14")
                .then()
                .log().body()
                .spec(resSpec)
                .extract().as(Dashboard.class);
        assertEquals(14,response.getId());

    }
}
