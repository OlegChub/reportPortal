import io.qameta.allure.Owner;
import models.Root;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.ConfigSetUp.getProjectName;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RestAssuredSpec.requestSpecification;
import static specs.RestAssuredSpec.resSpec;

public class ApiTests {
    @Test
    @DisplayName("Get all permitted dashboard resources for specified project")
    @Owner("Oleg Chubryk")
    public void getAllPermittedDashboard() {
        Root response =
                given(requestSpecification)
                        .when()
                        .get("api/v1/" + getProjectName() + "/dashboard")
                        .then()
                        .log().body()
                        .spec(resSpec)
                        .extract().as(Root.class);
        assertEquals(14, response.content.get(0).getId());
    }
}
