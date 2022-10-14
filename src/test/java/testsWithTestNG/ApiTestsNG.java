package testsWithTestNG;

import api.ApiClient;
import controllers.DashboardController;
import controllers.LaunchController;
import org.testng.annotations.Test;
import testData.DataProviderForTestNG;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;


public class ApiTestsNG extends BaseTestNG {
    private ApiClient api = new ApiClient();
    private DashboardController dashboards = new DashboardController(api);
    private LaunchController launch = new LaunchController(api);
    private String dashboardNameExpected = "DEMO DASHBOARD";
    private int launchId = 3;


    @Test(testName = "Get permitted dashboard for specified project")
    public void getAllPermittedDashboard() {
        assertThat(dashboards.getAllDashboardNames(), hasItem(dashboardNameExpected));
    }

    @Test(testName = "Check exact launch execution info with data provider",
            dataProvider = "testng", dataProviderClass = DataProviderForTestNG.class)
    public void getLaunchExecutionInfo(String infoBlockName, int expectedValue) {
        launch.getExactLaunchInfo(launchId);
        assertEquals(expectedValue, launch.getLaunchExecutionsInfoParameter(infoBlockName));
    }
}
