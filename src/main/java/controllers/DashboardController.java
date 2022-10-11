package controllers;

import api.ApiClient;
import api.request.GetDashboard;
import helpers.ResponseHandler;
import io.restassured.response.ValidatableResponse;
import models.Content;
import models.Dashboards;

import java.util.ArrayList;
import java.util.List;

public class DashboardController {
    ApiClient client;

    public DashboardController(ApiClient client) {
        this.client = client;
    }

    public ValidatableResponse getDashboards() {
        return client.execute(new GetDashboard());
    }

    public ArrayList<String> getAllDashboardNames() {
        Dashboards dashboards = ResponseHandler.getResponseAsClass(Dashboards.class,
                getDashboards().extract().response());
        List<Content> contentList = dashboards.getContent();
        ArrayList<String> listWithDashboardNames = new ArrayList<>();
        for (int i = 0; i < contentList.size(); i++) {
            listWithDashboardNames.add(contentList.get(i).getName());
        }
        return listWithDashboardNames;
    }

    public boolean hasDashboardWithName(List<String> listWithDashboardNames, String dashboardName) {
        return listWithDashboardNames.contains(dashboardName);
    }

}
