package com.danb.dca.registry_service.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

import static com.danb.dca.registry_service.utils.ConstantStrings.*;

@Data
@Builder
public class RegistryAuthRequest {
    private static final String EMAIL = "email";
    private static final String APPLICATION_ID = "application_id";
    private static final String PATTERN_APPLICATION_ID = "dca-react-app | local-postman";

    @Email
    @NotNull(message = NOT_NULL_EMAIL_MESSAGE)
    @JsonProperty(EMAIL)
    private String email;

    @NotNull(message = NOT_NULL_APPLICATION_ID_MESSAGE)
    @JsonProperty(APPLICATION_ID)
    @Pattern(regexp = PATTERN_APPLICATION_ID, message = PATTERN_APPLICATION_ID_MESSAGE)
    private String applicationId;
}
