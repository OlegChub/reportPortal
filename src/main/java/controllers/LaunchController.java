package controllers;

import api.ApiClient;
import api.request.GetLaunchInfo;
import helpers.ResponseHandler;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import models.launch.defects.LaunchDefects;
import models.launch.Launch;
import models.launch.LaunchExecutions;
import models.launch.LaunchStatistics;

public class LaunchController {
    private ApiClient client;
    private ValidatableResponse validatableResponse;
    private Response response;

    public LaunchController(ApiClient client) {
        this.client = client;
    }

    public void getExactLaunchInfo(int id) {
        this.validatableResponse = client.execute(new GetLaunchInfo(id));
        this.response = validatableResponse.extract().response();
    }

    public LaunchStatistics getLaunchStatisticsInfo() {
        Launch launchInfo = ResponseHandler.getResponseAsClass(Launch.class,
                this.response);
        return launchInfo.getStatistics();
    }

    public LaunchDefects getLaunchDefectsInfo() {
        return getLaunchStatisticsInfo().getDefects();
    }

    public LaunchExecutions getExecutionsInfo() {
        return getLaunchStatisticsInfo().getExecutions();
    }

    public int getLaunchExecutionsInfoParameter(String parameterName) {
        LaunchExecutions launchExecutions = getExecutionsInfo();
        switch (parameterName) {
            case "total":
                return launchExecutions.getTotal();
            case "failed":
                return launchExecutions.getFailed();
            case "passed":
                return launchExecutions.getPassed();
            case "skipped":
                return launchExecutions.getSkipped();
            default:
                System.out.println("There is no such parameter in launch executions info block");
        }

        return -1;
    }

    public int getLaunchDefectsInfoParameter(String parameterName) {
        LaunchDefects launchDefects = getLaunchDefectsInfo();

        if (parameterName.equals("productBug")) {
            return launchDefects.getProductBug().getTotal();
        } else if (parameterName.equals("toInvestigate")) {
            return launchDefects.getToInvestigate().getTotal();
        } else if (parameterName.equals("automationBug")) {
            return launchDefects.getAutomationBug().getTotal();
        }

        return -1;
    }
}
