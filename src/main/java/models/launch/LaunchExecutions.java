package models.launch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LaunchExecutions {
    public int total;
    public int failed;
    public int passed;
    public int skipped;
}
