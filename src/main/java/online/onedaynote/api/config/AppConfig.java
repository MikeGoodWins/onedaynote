package online.onedaynote.api.config;

import java.util.Map;
import java.util.Objects;
import lombok.NoArgsConstructor;
import online.onedaynote.api.exceptions.NoteException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

/**
 * App configuration.
 */
@Configuration
@NoArgsConstructor
public class AppConfig {


    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(
                    final WebRequest webRequest, final ErrorAttributeOptions options) {
                Map<String, Object> errorAttr = super.getErrorAttributes(webRequest, options);
                Throwable error = getError(webRequest);
                errorAttr.put("message", Objects.isNull(error) ? "NoMessage" : error.getMessage());
                if(error instanceof NoteException){
                    errorAttr.put("code", ((NoteException) error).getCode());
                    errorAttr.remove("trace");
                }
                return errorAttr;
            }

        };
    }
}
