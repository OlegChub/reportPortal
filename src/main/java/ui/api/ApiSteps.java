package ui.api;

import controllers.WidgetController;
import exeptions.ProceedFailedException;
import io.restassured.response.ValidatableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;

public class ApiSteps {
    private static final Logger LOGGER = LogManager.getLogger();

    public int createAndAddWidget(WidgetController widget) {
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
}
