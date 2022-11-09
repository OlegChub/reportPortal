package httpclient.helpers;

import models.widget.ContentParameters;
import models.widget.Filter;
import models.widget.Widget;
import models.widget.WidgetOptions;
import org.json.JSONObject;

import java.util.ArrayList;

import static httpclient.Utils.NameGenerator.generateName;

public class JSONTestData {

    static public JSONObject getNewDashboardJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("description", generateName());
        jsonObject.put("name", generateName());
        jsonObject.put("share", false);

        return jsonObject;
    }

    static public JSONObject getNewWidgetJSON() {
        Widget widget = new Widget();
        ContentParameters contentParameters = new ContentParameters();
        WidgetOptions widgetOptions = new WidgetOptions();

        widget.setName(generateName());
        widget.setDescription(generateName());
        widget.setFilterIds(new ArrayList<>());
        widget.getFilterIds().add("1");
        widget.setFilters(new ArrayList<>());
        widget.getFilters().add(new Filter());
        contentParameters.setWidgetOptions(widgetOptions);
        widget.setContentParameters(contentParameters);

        return new JSONObject(widget);
    }

    static public String getJSONValueByKey(String key, JSONObject json) {
        return String.valueOf(json.get(key));
    }

}
