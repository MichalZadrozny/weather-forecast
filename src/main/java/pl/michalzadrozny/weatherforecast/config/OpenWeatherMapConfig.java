package pl.michalzadrozny.weatherforecast.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api")
//@EncryptablePropertySource(name="EncryptedProperty", value="application.properties")
@Getter
@Setter
public class OpenWeatherMapConfig {

    private String key;
    private String url;
}
