import controllers.DashboardController;
import controllers.LaunchController;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTests extends BaseTest {
    private DashboardController dashboards = new DashboardController(api);
    private LaunchController launch = new LaunchController(api);
    private String dashboardNameExpected = "DEMO DASHBOARD";
    private int launchId = 3;

    @Test
    @DisplayName("Get all permitted dashboard resources for specified project")
    @Owner("Oleg Chubryk")
    public void getAllPermittedDashboard() {
        assertThat(dashboards.getAllDashboardNames(), hasItem(dashboardNameExpected));
    }

    @ParameterizedTest(name = "Parameter {0} has meaning {1}")
    @MethodSource("testData.DataProvider#dataForLaunchExecutionsTest")
    @DisplayName("Check exact launch execution info with data provider")
    @Owner("Oleg Chubryk")
    public void getLaunchExecutionInfo(String infoBlockName, int expectedValue) {
        launch.getExactLaunchInfo(launchId);
        assertEquals(expectedValue, launch.getLaunchExecutionsInfoParameter(infoBlockName));
    }

    @ParameterizedTest(name = "Parameter {0} has meaning {1}")
    @CsvFileSource(resources = "/testData/dataForLaunchDefectsTest.csv", numLinesToSkip = 1)
    @DisplayName("Check exact launch defects info with csv data file")
    @Owner("Oleg Chubryk")
    public void getLaunchDefectsInfo(String infoBlockName, int expectedValue) {
        launch.getExactLaunchInfo(launchId);
        assertEquals(expectedValue, launch.getLaunchDefectsInfoParameter(infoBlockName));
    }

}
