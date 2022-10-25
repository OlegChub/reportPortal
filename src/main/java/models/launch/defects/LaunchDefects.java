package models.launch.defects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaunchDefects {
    @JsonProperty("product_bug")
    private ProductBug productBug;

    @JsonProperty("to_investigate")
    private ToInvestigate toInvestigate;

    @JsonProperty("automation_bug")
    private AutomationBug automationBug;
}
