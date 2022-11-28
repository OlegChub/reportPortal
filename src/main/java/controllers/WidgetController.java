package controllers;

import api.ApiClient;
import api.request.DeleteWidget;
import api.request.PostWidget;
import api.request.PutWidget;
import constants.Constants;
import io.restassured.response.ValidatableResponse;

public class WidgetController {

    private ApiClient client;

    public WidgetController(ApiClient client) {
        this.client = client;
    }

    public ValidatableResponse createWidget() {
        return client.execute(new PostWidget());
    }

    public ValidatableResponse addWidgetToDashboard(int widgetId) {
        return client.execute(new PutWidget(widgetId));
    }

    public ValidatableResponse deleteWidget(int widgetId) {
        return client.execute(new DeleteWidget(Constants.DASHBOARD_ID, widgetId));
    }
}
