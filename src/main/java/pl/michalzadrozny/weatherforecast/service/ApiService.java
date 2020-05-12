package pl.michalzadrozny.weatherforecast.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.michalzadrozny.weatherforecast.config.OpenWeatherMapConfig;

@Service
public class ApiService {

    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    public ApiService(OpenWeatherMapConfig openWeatherMapConfig) {
        this.openWeatherMapConfig = openWeatherMapConfig;
    }

    public String createUri(String city, String units){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getApiUrl())
                .queryParam("q", city)
                .queryParam("appid", openWeatherMapConfig.getApiKey())
                .queryParam("units", units)
                .buildAndExpand();

        return builder.toUriString();
    }
}
