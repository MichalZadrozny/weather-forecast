package pl.michalzadrozny.weatherforecast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.michalzadrozny.weatherforecast.config.OpenWeatherMapConfig;
import pl.michalzadrozny.weatherforecast.config.WeatherDisplay;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

@Controller
public class WeatherForecastController {

    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    private WeatherDisplay weatherDisplay;
    private boolean isSetAsCelsius;
    private String city;


    public WeatherForecastController() {
    }

    @Autowired
    public WeatherForecastController(OpenWeatherMapConfig openWeatherMapConfig ) {
        this.openWeatherMapConfig = openWeatherMapConfig;
        this.isSetAsCelsius = true;
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @PostMapping("/")
    public String sendCityName(String city, Model model){
        this.city = city;
        return "redirect:/weather";
    }

    @GetMapping("/weather")
    public String showWeatherPage(Model model) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getApiUrl()).buildAndExpand(city, openWeatherMapConfig.getApiKey());
        RestTemplate restTemplate = new RestTemplate();
        CurrentWeather currentWeather = restTemplate.getForObject(uriComponents.toString(), CurrentWeather.class);

        weatherDisplay.setCurrentWeather(currentWeather);
        weatherDisplay.setTemperatures(isSetAsCelsius);

        model.addAttribute("currentWeather",currentWeather);

        return "weather";
    }
}
