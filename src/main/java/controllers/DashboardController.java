package controllers;

import api.ApiClient;
import api.request.GetDashboard;
import helpers.ResponseHandler;
import io.restassured.response.ValidatableResponse;
import models.Dashboards;

public class DashboardController {
    ApiClient client;

    public DashboardController(ApiClient client) {
        this.client = client;
    }

    public ValidatableResponse getDashboards() {
        return client.execute(new GetDashboard());
    }

    public String getDashboardName() {
        Dashboards dashboards = ResponseHandler.getResponseAsClass(Dashboards.class,
                getDashboards().extract().response());
        return dashboards.getContent().get(0).getName();
    }
}
