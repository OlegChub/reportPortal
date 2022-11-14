package cucumber.steps;

import api.ApiClient;
import controllers.DashboardController;
import controllers.LaunchController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static helpers.UserLogin.getToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StepDefinitions {
    private ApiClient api = new ApiClient();
    private int launchId;
    private String parameter;
    private LaunchController launch = new LaunchController(api);
    private DashboardController dashboards = new DashboardController(api);
    private ArrayList<String> listWithDashboardNames;

    @When("user is logged in")
    public void checkUserIsLoggedIn() {
        assertNotNull(getToken(), "Authorization failed");
    }

    @When("user makes request to get all permitted dashboard resources for the project")
    public void getAllPermittedDashboardResourcesForTheProject() {
        listWithDashboardNames = dashboards.getAllDashboardNames();
    }

    @When("user sees dashboard with {string} name")
    public void checkDashboardNameIsInList(String dashboardName) {
        assertThat(listWithDashboardNames, hasItem(dashboardName));
    }

    @When("user makes request to get info of launch with id {int}")
    public void userMakesGetRequestToGetInfoOfLaunchWithId(int launchId) {
        launch.getExactLaunchInfo(launchId);
    }

    @When("user sees that parameter {string} has value {int}")
    public void checkParameterHasValue(String parameterName, int expectedValue) {
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(parameterName));
    }

    @When("user has initial data")
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

    @When("user sees that parameter value is {int}")
    public void userSeesThatParameterValueIs(int expectedValue) {
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(parameter));
    }
}
