package pl.michalzadrozny.weatherforecast.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.michalzadrozny.weatherforecast.model.CurrentWeather;

import static java.lang.Math.round;

@Component
@Setter
@Getter
@ToString
public class WeatherDisplay {

    CurrentWeather currentWeather;

    @Autowired
    public WeatherDisplay(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    //    public void setTemperatures(boolean isSetAsCelsius) {
//        if (isSetAsCelsius) {
//            currentWeather.setCurrentTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTemp()) + " °C");
//            currentWeather.setMinTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTempMin()) + " °C");
//            currentWeather.setMaxTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getTempMax()) + " °C");
//            currentWeather.setPerceptibleTemperature(currentWeather.getMain().kelvinToCelsius(currentWeather.getMain().getFeelsLike()) + " °C");
//
//
//            System.out.println("Celsius: " + currentWeather.getCurrentTemperature());
//        } else {
//            currentWeather.setCurrentTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTemp()) + " °F");
//            currentWeather.setMinTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTempMin()) + " °F");
//            currentWeather.setMaxTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getTempMax()) + " °F");
//            currentWeather.setPerceptibleTemperature(currentWeather.getMain().kelvinToFahrenheit(currentWeather.getMain().getFeelsLike()) + " °F");
//            System.out.println("Fahrenheit: " + currentWeather.getCurrentTemperature());
//
//        }
//    }
    public void setTemperatures(boolean isSetAsCelsius) {
        if (isSetAsCelsius) {
            currentWeather.setCurrentTemperature( round(currentWeather.getMain().getTemp()) + " °C");
            currentWeather.setMinTemperature(round(currentWeather.getMain().getTempMin()) + " °C");
            currentWeather.setMaxTemperature(round(currentWeather.getMain().getTempMax()) + " °C");
            currentWeather.setPerceptibleTemperature(round(currentWeather.getMain().getFeelsLike()) + " °C");

            System.out.println("Celsius: " + currentWeather.getCurrentTemperature());
        } else {
            currentWeather.setCurrentTemperature(round(currentWeather.getMain().getTemp()) + " °F");
            currentWeather.setMinTemperature(round(currentWeather.getMain().getTempMin()) + " °F");
            currentWeather.setMaxTemperature(round(currentWeather.getMain().getTempMax()) + " °F");
            currentWeather.setPerceptibleTemperature(round(currentWeather.getMain().getFeelsLike()) + " °F");

            System.out.println("Fahrenheit: " + currentWeather.getCurrentTemperature());
        }
    }
//
//    public boolean changeToCelsius() {
//        System.out.println("Before changing to Celsius");
//        setTemperatures(true);
//        System.out.println("Changed to Celsius");
//        return true;
//    }
//
//    public boolean changeToFahrenheit() {
//        System.out.println("Before changing to Fahrenheit");
//
//        setTemperatures(false);
//        System.out.println("Changed to Fahrenheit");
//        return false;
//    }

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

}
