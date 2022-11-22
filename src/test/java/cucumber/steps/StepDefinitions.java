package cucumber.steps;

import api.ApiClient;
import controllers.DashboardController;
import controllers.LaunchController;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.launch.defects.DefectsInfo;
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
    private DefectsInfo defectsInfo;
    private int launchId;
    private String defectName;
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
        LOGGER.info("Getting all permitted dashboard resources ...");
        listWithDashboardNames = dashboards.getAllDashboardNames();
        LOGGER.info("All permitted dashboard resources were successfully got");
    }

    @Then("user sees dashboard with {string} name")
    public void checkDashboardNameIsInList(String dashboardName) {
        LOGGER.info("Checking dashboard name is in list ...");
        assertThat(listWithDashboardNames, hasItem(dashboardName));
        LOGGER.info("Dashboard name is in list");
    }

    @When("user makes request to get info of launch with id {int}")
    public void userMakesGetRequestToGetInfoOfLaunchWithId(int launchId) {
        LOGGER.info("Getting launch info ...");
        launch.getExactLaunchInfo(launchId);
        LOGGER.info("Launch info was successfully got");
    }

    @Then("user sees that parameter {string} has value {int}")
    public void checkParameterHasValue(String parameterName, int expectedValue) {
        LOGGER.info("Checking parameter value ...");
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(parameterName));
        LOGGER.info("Parameter value was successfully checked");
    }

    @Given("user has initial data")
    public void getInitialData(DataTable table) {
        LOGGER.info("Getting data from data table ...");
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            this.launchId = Integer.parseInt(columns.get("launchId"));
        }
        LOGGER.info("Data was successfully saved");
    }

    @When("user makes the request")
    public void makeRequest() {
        LOGGER.info("Making request ...");
        launch.getExactLaunchInfo(launchId);
        LOGGER.info("Request was successfully made");
    }

    @DataTableType
    public DefectsInfo userEntryTransformer(Map<String, String> row) {
        LOGGER.info("Transforming data ...");
        defectName=row.get("defectName");
        LOGGER.info("Data was successfully transformed");
        return new DefectsInfo(row.get("defectName"), row.get("quantity"));
    }

    @Given("user has data of the defect")
    public void userHasDefectData(List<DefectsInfo> defectsInfoList) {
        LOGGER.info("Handling defect data ...");
        for (DefectsInfo defectsInfo : defectsInfoList) {
            this.defectsInfo = defectsInfo;
        }
        LOGGER.info("Defect data was successfully handled");
    }

    @Then("user sees that parameter value in response is equal")
    public void userSeesThatParameterInResponseIsEqual() {
        LOGGER.info("Checking parameter value ...");
        assertEquals(Integer.parseInt(defectsInfo.getProductBugQuantity()), launch.getLaunchDefectsInfoParameter(defectName));
        LOGGER.info("Parameter value was successfully checked");
    }

}
