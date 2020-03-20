package pl.michalzadrozny.weatherforecast.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

@Component
@Setter
@Getter
@ToString
public class WeatherDisplay{

    CurrentWeather currentWeather;

    @Autowired
    public WeatherDisplay(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public void setTemperatures(boolean isSetAsCelsius){
        if(isSetAsCelsius){
            currentWeather.setCurrentTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTemp())+" °C");
            currentWeather.setMinTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTempMin())+" °C");
            currentWeather.setMaxTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTempMax())+" °C");

            System.out.println("Celsius: "+currentWeather.getCurrentTemperature());
        }else{
            currentWeather.setCurrentTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTemp())+" °F");
            currentWeather.setMinTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTempMin())+" °F");
            currentWeather.setMaxTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTempMax())+" °F");
            System.out.println("Fahrenheit: "+currentWeather.getCurrentTemperature());

        }
    }

    public boolean changeToCelsius(){
        setTemperatures(true);
        return true;
    }

    public boolean changeToFahrenheit(){
        setTemperatures(false);
        System.out.println("Changed to Fahrenheit");
        return false;
    }

}
