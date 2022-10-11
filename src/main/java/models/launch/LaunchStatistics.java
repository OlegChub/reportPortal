package models.launch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.launch.defects.LaunchDefects;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaunchStatistics {
    private LaunchExecutions executions;
    private LaunchDefects defects;
}
