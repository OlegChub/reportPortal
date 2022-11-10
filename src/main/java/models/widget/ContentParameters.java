package models.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentParameters {
    private final String itemsCount = "10";
    private WidgetOptions widgetOptions;
}
