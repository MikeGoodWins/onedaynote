package online.onedaynote.api.config;

import static online.onedaynote.api.rest.Paths.NOTES;
import static online.onedaynote.api.rest.Paths.PARAMS;
import static online.onedaynote.api.rest.Paths.ROOT;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Web-security configuration.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@NoArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String USER_ROLE = "USER";
    private static final String MODERATOR_ROLE = "MODERATOR";
    private static final String TESTER_ROLE = "TESTER";
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String[] AUTHORIZED = {USER_ROLE, MODERATOR_ROLE, TESTER_ROLE, ADMIN_ROLE};
    private static final String[] ADMINISTRATION = {TESTER_ROLE, ADMIN_ROLE};
    private static final String[] PRIVILEGES = {MODERATOR_ROLE, TESTER_ROLE, ADMIN_ROLE};

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .mvcMatchers(HttpMethod.GET, NOTES.concat(ROOT)).permitAll()
                .mvcMatchers(HttpMethod.POST, NOTES.concat(ROOT)).permitAll()
                .mvcMatchers(HttpMethod.GET, NOTES.concat(PARAMS)).permitAll();
    }
}
