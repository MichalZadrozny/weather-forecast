package pl.michalzadrozny.weatherforecast.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "temp",
        "feels_like",
        "temp_min",
        "temp_max",
        "pressure",
        "humidity"
})
public class Main {

    @JsonProperty("temp")
    private Double temp;
    @JsonProperty("feels_like")
    private Double feelsLike;
    @JsonProperty("temp_min")
    private Double tempMin;
    @JsonProperty("temp_max")
    private Double tempMax;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public int kelvinToCelsius(Double kelvin){
        double celsius = kelvin - 273.15;
        int result = (int) Math.round(celsius);

        return result;
    }

    public int kelvinToFahrenheit(Double kelvin){
        double fahrenheit = (9/5) * (kelvin - 273.15) + 32;
        int result = (int) fahrenheit;

        return result;
    }

}