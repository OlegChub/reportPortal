package models.widget;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Filter {
    private final String value = "1";
    private final String name = "DEMO_FILTER";
}
