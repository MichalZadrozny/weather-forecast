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
        "3h"
})
public class Rain {

    @JsonProperty("3h")
    private Double _3h;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

}