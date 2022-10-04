package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Content {
    public String owner;
    public boolean share;
    public int id;
    public String name;
    public String description;
}
