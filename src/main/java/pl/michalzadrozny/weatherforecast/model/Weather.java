package pl.michalzadrozny.weatherforecast.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "main",
        "description",
        "icon"
})
public class Weather {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("main")
    private String main;
    @JsonProperty("description")
    private String description;
    @JsonProperty("icon")
    private String icon;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public void setDescription(String description) {
        this.description = description.substring(0,1).toUpperCase() + description.substring(1);
    }
}