package pl.michalzadrozny.weatherforecast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.michalzadrozny.weatherforecast.config.OpenWeatherMapConfig;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

@Service
public class ApiService {

    private final OpenWeatherMapConfig openWeatherMapConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public ApiService(OpenWeatherMapConfig openWeatherMapConfig) {
        this.openWeatherMapConfig = openWeatherMapConfig;
        this.restTemplate = new RestTemplate();
    }

    public String createUri(String city, String units){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getUrl())
                .queryParam("q", city)
                .queryParam("appid", openWeatherMapConfig.getKey())
                .queryParam("units", units)
                .buildAndExpand();

        return builder.toUriString();
    }

    public CurrentWeather getWeather(String city){
        return restTemplate.getForObject(createUri(city,"metric"),CurrentWeather.class);
    }
}
