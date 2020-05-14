package pl.michalzadrozny.weatherforecast.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.michalzadrozny.weatherforecast.config.WeatherDisplay;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;
import pl.michalzadrozny.weatherforecast.service.ApiService;

@Slf4j
@Controller
public class WeatherForecastController {

    private ApiService apiService;
    private WeatherDisplay weatherDisplay;
    private boolean isSetAsCelsius;
    private RestTemplate restTemplate;


    public WeatherForecastController() {
        restTemplate = new RestTemplate();
    }

    @Autowired
    public WeatherForecastController(ApiService apiService, WeatherDisplay weatherDisplay) {
        this.apiService = apiService;
        this.isSetAsCelsius = true;
        this.weatherDisplay = weatherDisplay;
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/")
    public String indexPage(){
        return "index";
    }

    @PostMapping("/")
    public String sendCityName(RedirectAttributes redirectAttributes, String city){
        redirectAttributes.addAttribute("city",city);

        return "redirect:/weather";
    }



    @GetMapping("/weather")
    public String showWeatherPage(@RequestParam String city, Model model) {
        try{
            CurrentWeather currentWeather = restTemplate.getForObject(apiService.createUri(city,"metric"),CurrentWeather.class);

            weatherDisplay.setCurrentWeather(currentWeather);
            weatherDisplay.setTemperatures(isSetAsCelsius);

            model.addAttribute("currentWeather",currentWeather);
            model.addAttribute("weatherDisplay",weatherDisplay);

            return "weather";
        }catch (Exception e){
            log.error(String.valueOf(e.getMessage()));
        }

        return "index";
    }
}
