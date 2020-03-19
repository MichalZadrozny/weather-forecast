package pl.michalzadrozny.weatherforecast.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EncryptablePropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EncryptablePropertySource(name="EncryptedProperty", value="application.properties")
public class OpenWeatherMapConfig {

    @Value("${api.encrypted.apiKey}")
    private String apiKey;

    @Value("${api.url}")
    private String apiUrl;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiUrl() {
        return apiUrl;
    }
}
