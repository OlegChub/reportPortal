package cucumber.steps;

import controllers.DashboardController;
import controllers.LaunchController;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static helpers.UserTokenGenerator.getToken;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions {
    private LaunchController launch = new LaunchController(Hooks.api);
    private DashboardController dashboards = new DashboardController(Hooks.api);
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

}
