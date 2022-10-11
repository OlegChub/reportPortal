import api.ApiClient;
import controllers.DashboardController;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTests extends BaseTest{
    private ApiClient api = new ApiClient();
    private DashboardController dashboards = new DashboardController(api);
    private String dashboardNameExpected = "DEMO DASHBOARD";

    @Test
    @DisplayName("Get all permitted dashboard resources for specified project")
    @Owner("Oleg Chubryk")
    public void getAllPermittedDashboard() {
        assertTrue(dashboards.hasDashboardWithName(dashboards.getAllDashboardNames(),dashboardNameExpected));

    }
}
