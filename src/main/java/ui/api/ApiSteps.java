package ui.api;

import api.ApiClient;
import controllers.WidgetController;
import exeptions.FailedToLoginException;
import exeptions.ProceedFailedException;
import helpers.UserLogin;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;

public class ApiSteps {
    private ApiClient api;
    private WidgetController widget;
    private static final Logger LOGGER = LogManager.getLogger();

    public ApiSteps() {
        this.api = new ApiClient();
        this.widget = new WidgetController(this.api);
    }

    public int createAndAddWidget() {
        LOGGER.info("Creating widget ...");
        ValidatableResponse response = widget.createWidget();
        if (response.extract().statusCode() == 201) {
            LOGGER.info("Widget successfully created");
        } else {
            LOGGER.error("Failed to create widget");
            throw new ProceedFailedException("Widget creation failed");
        }
        JSONObject jsonObject = new JSONObject(response.extract().response().asString());
        int widgetId = Integer.parseInt(getJSONValueByKey("id", jsonObject));
        LOGGER.info("Adding widget to dashboard ...");
        ValidatableResponse addWidgetResponse = widget.addWidgetToDashboard(widgetId);
        if (addWidgetResponse.extract().statusCode() == 200) {
            LOGGER.info("Widget successfully added");
        } else {
            LOGGER.error("Failed to add widget on the dashboard");
            throw new ProceedFailedException("Failed to add widget on the dashboard");
        }
        return widgetId;
    }

    public void deleteWidgetWithId(int widgetId) {
        LOGGER.info("Removing test data ...");
        if (widget.deleteWidget(widgetId).extract().statusCode() == 200) {
            LOGGER.info("Widget successfully removed");
        } else {
            LOGGER.error("Failed to remove widget");
            throw new ProceedFailedException("Failed to remove widget");
        }
    }

    public void loginAsUser() throws FailedToLoginException {
        UserLogin userLogin = new UserLogin(api);
        userLogin.login();
    }
}
