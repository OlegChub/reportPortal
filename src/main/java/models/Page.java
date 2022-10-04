package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Page {
    public int number;
    public int size;
    public int totalElements;
    public int totalPages;
}
