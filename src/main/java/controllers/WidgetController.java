package controllers;

import api.ApiClient;
import api.request.DeleteWidgetRequest;
import api.request.PostWidgetRequest;
import api.request.PutWidgetRequest;
import constants.Constants;
import io.restassured.response.ValidatableResponse;

public class WidgetController {

    private ApiClient client;

    public WidgetController(ApiClient client) {
        this.client = client;
    }

    public ValidatableResponse createWidget() {
        return client.execute(new PostWidgetRequest());
    }

    public ValidatableResponse addWidgetToDashboard(int widgetId) {
        return client.execute(new PutWidgetRequest(widgetId));
    }

    public ValidatableResponse deleteWidget(int widgetId) {
        return client.execute(new DeleteWidgetRequest(Constants.DASHBOARD_ID, widgetId));
    }
}
