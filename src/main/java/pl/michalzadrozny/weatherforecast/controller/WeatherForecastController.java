package pl.michalzadrozny.weatherforecast.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.michalzadrozny.weatherforecast.config.WeatherDisplay;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;
import pl.michalzadrozny.weatherforecast.service.ApiService;
import pl.michalzadrozny.weatherforecast.service.PopularCitiesService;

import java.util.Optional;

@Slf4j
@Controller
public class WeatherForecastController {

    private ApiService apiService;
    private WeatherDisplay weatherDisplay;
    private boolean isSetAsCelsius;
    private PopularCitiesService popularCitiesService;

    public WeatherForecastController() {
    }

    @Autowired
    public WeatherForecastController(ApiService apiService, WeatherDisplay weatherDisplay, PopularCitiesService popularCitiesService) {
        this.apiService = apiService;
        this.isSetAsCelsius = true;
        this.weatherDisplay = weatherDisplay;
        this.popularCitiesService = popularCitiesService;
    }

    @GetMapping("/")
    public String indexPage(Model model, @RequestParam(required = false, defaultValue = "true") boolean found){

        if(found==false){
            model.addAttribute("found",false);
        }

        if(popularCitiesService.getCities().isEmpty()){
            String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
            popularCitiesService.setPopularCities(baseUrl, isSetAsCelsius);
        }
        model.addAttribute("popularCities", popularCitiesService);
        return "index";
    }

    @PostMapping("/")
    public String sendCityName(RedirectAttributes redirectAttributes, String city){
        redirectAttributes.addAttribute("city",city);
        return "redirect:/weather/{city}";
    }

    @GetMapping("/weather/{city}")
    public String showWeatherPage(@PathVariable String city, Model model, RedirectAttributes redirectAttributes) {
        try{
            CurrentWeather currentWeather = apiService.getWeather(city);
            log.info(currentWeather.toString());


                weatherDisplay.setCurrentWeather(currentWeather);
                weatherDisplay.setTemperatures(isSetAsCelsius);

                model.addAttribute("currentWeather",currentWeather);
                model.addAttribute("weatherDisplay",weatherDisplay);
                return "weather";

        }catch (Exception e){
            log.error(String.valueOf(e.getMessage()));
        }
        redirectAttributes.addFlashAttribute("found",false);
        return "redirect:/";
    }

    @PostMapping("/weather/{city}")
    public String resendCityName(RedirectAttributes redirectAttributes, String city){
        redirectAttributes.addAttribute("city",city);
        return "redirect:/weather/{city}";
    }

}
