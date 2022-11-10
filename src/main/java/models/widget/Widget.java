package models.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Widget {
    private final String widgetType = "uniqueBugTable";
    private ContentParameters contentParameters;
    private ArrayList<Filter> filters;
    private String name;
    private String description;
    private final boolean share = true;
    private ArrayList<String> filterIds;
}
