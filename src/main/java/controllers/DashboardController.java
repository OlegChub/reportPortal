package controllers;

import api.ApiClient;
import api.request.GetDashboardRequest;
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
        return client.execute(new GetDashboardRequest());
    }

    public ArrayList<String> getAllDashboardNames() {
        Dashboards dashboards = ResponseHandler.getResponseAsClass(Dashboards.class,
                getDashboards().extract().response());
        List<Content> contentList = dashboards.getContent();
        ArrayList<String> listWithDashboardNames = new ArrayList<>();
        for (Content dashboardItem : contentList) {
            listWithDashboardNames.add(dashboardItem.getName());
        }
        return listWithDashboardNames;
    }

}
