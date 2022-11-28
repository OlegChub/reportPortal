package ui.api;

import controllers.WidgetController;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;

import static httpclient.helpers.JSONHelper.getJSONValueByKey;
import static ui.logger.UILogger.LOGGER;

public class ApiSteps {

    public int createAndAddWidget(WidgetController widget) {
        LOGGER.info("Creating widget ...");
        ValidatableResponse response = widget.createWidget();
        LOGGER.info("Widget successfully created");
        JSONObject jsonObject = new JSONObject(response.extract().response().asString());
        int widgetId = Integer.parseInt(getJSONValueByKey("id", jsonObject));
        LOGGER.info("Adding widget to dashboard ...");
        widget.addWidgetToDashboard(widgetId);
        LOGGER.info("Widget successfully added");
        return widgetId;
    }
}
