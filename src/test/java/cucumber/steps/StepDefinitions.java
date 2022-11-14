package cucumber.steps;

import api.ApiClient;
import controllers.DashboardController;
import controllers.LaunchController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static helpers.UserLogin.getToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinitions {
    private static final Logger LOGGER = LogManager.getLogger();
    private ApiClient api = new ApiClient();
    private int launchId;
    private String parameter;
    private LaunchController launch = new LaunchController(api);
    private DashboardController dashboards = new DashboardController(api);
    private ArrayList<String> listWithDashboardNames;

    @Given("user is logged in")
    public void checkUserIsLoggedIn() {
        LOGGER.info("Checking access token ...");
        assertNotNull(getToken(), "Authorization failed");
        LOGGER.info("Access token is actual");
    }

    @When("user makes request to get all permitted dashboard resources for the project")
    public void getAllPermittedDashboardResourcesForTheProject() {
        listWithDashboardNames = dashboards.getAllDashboardNames();
    }

    @Then("user sees dashboard with {string} name")
    public void checkDashboardNameIsInList(String dashboardName) {
        assertThat(listWithDashboardNames, hasItem(dashboardName));
    }

    @When("user makes request to get info of launch with id {int}")
    public void userMakesGetRequestToGetInfoOfLaunchWithId(int launchId) {
        launch.getExactLaunchInfo(launchId);
    }

    @Then("user sees that parameter {string} has value {int}")
    public void checkParameterHasValue(String parameterName, int expectedValue) {
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(parameterName));
    }

    @Given("user has initial data")
    public void getInitialData(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            this.launchId = Integer.parseInt(columns.get("launchId"));
            this.parameter = columns.get("parameter");
        }
    }

    @When("user makes the request")
    public void makeRequest() {
        launch.getExactLaunchInfo(launchId);
    }

    @Then("user sees that parameter value is {int}")
    public void userSeesThatParameterValueIs(int expectedValue) {
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(parameter));
    }
}
