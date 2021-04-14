package online.onedaynote.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Web-configuration.
 */

@Configuration
public class WebConfig{

    private final ObjectMapper jacksonObjectMapper;

    @Autowired
    public WebConfig(final ObjectMapper jacksonObjectMapper) {
        this.jacksonObjectMapper = jacksonObjectMapper;
    }
}