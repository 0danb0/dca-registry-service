package com.danb.dca.registry_service.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class AuthHelper {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String createUserToken(String email, String applicationId) {
        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(60 * 30))
                .subject(email)
                .claim("role", "user")
                .claim("application_id", "applicationId")
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public Jwt validateToken(String token) {
        return jwtDecoder.decode(token);
    }

}
