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
        "type",
        "id",
        "country",
        "sunrise",
        "sunset"
})
public class Sys {

    @JsonProperty("type")
    private Integer type;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("country")
    private String country;
    @JsonProperty("sunrise")
    private Integer sunrise;
    @JsonProperty("sunset")
    private Integer sunset;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}