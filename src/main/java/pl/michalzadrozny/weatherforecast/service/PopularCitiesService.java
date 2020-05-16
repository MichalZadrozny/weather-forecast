package pl.michalzadrozny.weatherforecast.service;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.michalzadrozny.weatherforecast.config.WeatherDisplay;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@ToString
@Slf4j
public class PopularCitiesService {

    private final ApiService apiService;
    private final List<WeatherDisplay> cities;

    @Autowired
    public PopularCitiesService(ApiService apiService) {
        this.apiService = apiService;
        cities = new ArrayList<>();
    }

    public void setPopularCities(String url){
        WeatherDisplay city1 = new WeatherDisplay(apiService.getWeather("Warsaw"));
        WeatherDisplay city2 = new WeatherDisplay(apiService.getWeather("Tokyo"));
        WeatherDisplay city3 = new WeatherDisplay(apiService.getWeather("London"));
        WeatherDisplay city4 = new WeatherDisplay(apiService.getWeather("Washington"));

        city1.setLink(url);
        city2.setLink(url);
        city3.setLink(url);
        city4.setLink(url);

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
    }


}
