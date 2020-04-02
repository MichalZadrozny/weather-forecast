package pl.michalzadrozny.weatherforecast.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EncryptablePropertySource(name="EncryptedProperty", value="application.properties")
@Getter
public class OpenWeatherMapConfig {

    @Value("${api.encrypted.apiKey}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;
}
