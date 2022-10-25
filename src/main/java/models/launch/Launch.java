package models.launch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Launch {
    public int id;
    public String name;
    public LaunchStatistics statistics;
}
