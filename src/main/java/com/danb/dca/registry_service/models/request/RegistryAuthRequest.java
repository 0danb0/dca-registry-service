package com.danb.dca.registry_service.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryAuthRequest {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String APPLICATION_ID = "application_id";
    private static final String PATTERN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,20}$";
    private static final String PATTERN_APPLICATION_ID = "dca-react-app | local-postman";

    @Email
    @JsonProperty(EMAIL)
    private String email;

    @Pattern(regexp = PATTERN_PASSWORD)
    @JsonProperty(PASSWORD)
    private String password;

    @Pattern(regexp = PATTERN_APPLICATION_ID)
    @JsonProperty(APPLICATION_ID)
    private String applicationId;
}
