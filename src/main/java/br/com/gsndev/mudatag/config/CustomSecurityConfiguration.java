package br.com.gsndev.mudatag.config;

import br.com.gsndev.mudatag.core.filter.ValidateJwtUserAccessFilter;
import br.com.gsndev.mudatag.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfiguration {
    private final JwtDecoder jwtDecoder;
    private final UserService userService;

    public CustomSecurityConfiguration(JwtDecoder jwtDecoder, UserService userService) {
        this.jwtDecoder = jwtDecoder;
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .addFilterAfter(new ValidateJwtUserAccessFilter(this.jwtDecoder, this.userService), BearerTokenAuthenticationFilter.class);

        return http.build();
    }

}
