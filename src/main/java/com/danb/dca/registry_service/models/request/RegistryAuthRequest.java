package com.danb.dca.registry_service.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryAuthRequest {
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String APPLICATION_ID = "application_id";

    @JsonProperty(EMAIL)
    private String email;
    @JsonProperty(PASSWORD)
    private String password;
    @JsonProperty(APPLICATION_ID)
    private String applicationId;
}
