package pl.michalzadrozny.weatherforecast.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.michalzadrozny.weatherforecast.config.OpenWeatherMapConfig;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class WeatherForecastController {

    private OpenWeatherMapConfig openWeatherMapConfig;
    private String city;

    public WeatherForecastController() {
    }

    @Autowired
    public WeatherForecastController(OpenWeatherMapConfig openWeatherMapConfig ) {
        this.openWeatherMapConfig = openWeatherMapConfig;
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
//    @ResponseBody
    public String weatherPage(Model model) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getApiUrl()).buildAndExpand(city, openWeatherMapConfig.getApiKey());
        RestTemplate restTemplate = new RestTemplate();
        CurrentWeather currentWeather = restTemplate.getForObject(uriComponents.toString(), CurrentWeather.class);
//        currentWeather.setTemperatures();

        model.addAttribute(currentWeather);

        return "weather";
    }
}
