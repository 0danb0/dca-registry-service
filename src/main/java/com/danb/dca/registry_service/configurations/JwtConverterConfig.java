package com.danb.dca.registry_service.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collections;


@Configuration
public class JwtConverterConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String isActive = jwt.getClaimAsString("isActive");
            if (!"true".equalsIgnoreCase(isActive)) {
                throw new RuntimeException("User is not active");
            }

            String role = jwt.getClaimAsString("role");
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        });

        return converter;
    }

}