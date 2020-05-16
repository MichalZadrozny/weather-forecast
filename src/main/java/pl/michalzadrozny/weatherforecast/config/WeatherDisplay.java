package pl.michalzadrozny.weatherforecast.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

import static java.lang.Math.round;

@Component
@Getter
@ToString
@Slf4j
public class WeatherDisplay {

    @Setter
    CurrentWeather currentWeather;
    private String link;

    @Autowired
    public WeatherDisplay(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void setTemperatures(boolean isSetAsCelsius) {
        if (isSetAsCelsius) {
            currentWeather.setCurrentTemperature(round(currentWeather.getMain().getTemp()) + " °C");
            currentWeather.setMinTemperature(round(currentWeather.getMain().getTempMin()) + " °C");
            currentWeather.setMaxTemperature(round(currentWeather.getMain().getTempMax()) + " °C");
            currentWeather.setPerceptibleTemperature(round(currentWeather.getMain().getFeelsLike()) + " °C");
        } else {
            currentWeather.setCurrentTemperature(round(currentWeather.getMain().getTemp()) + " °F");
            currentWeather.setMinTemperature(round(currentWeather.getMain().getTempMin()) + " °F");
            currentWeather.setMaxTemperature(round(currentWeather.getMain().getTempMax()) + " °F");
            currentWeather.setPerceptibleTemperature(round(currentWeather.getMain().getFeelsLike()) + " °F");
        }
    }

    public String getWeatherImage() {

        int id = currentWeather.getWeather().get(0).getId();
        if (id >= 200 && id < 300) {
            return "img/weather/thunderstorm";
        } else if (id >= 300 && id < 400) {
            return "img/weather/rain";
        } else if (id >= 500 && id < 600) {
            return "img/weather/rain";
        } else if (id >= 600 && id < 700) {
            return "img/weather/snow";
        } else if (id == 781) {
            return "img/weather/tornado";
        } else if (id >= 700 && id < 800) {
            return "img/weather/atmosphere";
        } else if (id == 800) {
            return "img/weather/clear";
        } else if (id == 801 || id == 802) {
            return "img/weather/partly-cloudy";
        } else if (id == 803 || id == 804) {
            return "img/weather/cloudy";
        } else {
            return null;
        }
    }

    public void setLink(String url){
        this.link = url+"/"+currentWeather.getName();
    }

}
