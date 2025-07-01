package com.danb.dca.registry_service.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import static com.danb.dca.registry_service.utils.ConstantStrings.*;

@Data
public class InternalRegistryInsertRequest {
    private static final String EMAIL = "email";
    private static final String ACTIVE = "active";
    private static final String APPLICATION_ID = "application_id";
    private static final String PATTERN_APPLICATION_ID = "local-postman";
    private static final String PATTERN_ACTIVE = "true|false";

    @JsonProperty(APPLICATION_ID)
    @NotNull(message = NOT_NULL_APPLICATION_ID_MESSAGE)
    @Pattern(regexp = PATTERN_APPLICATION_ID, message = PATTERN_APPLICATION_ID_MESSAGE)
    private String applicationId;

    @Email(message = INVALID_EMAIL_MESSAGE)
    @NotNull(message = NOT_NULL_EMAIL_MESSAGE)
    @JsonProperty(EMAIL)
    private String email;

    @JsonProperty(ACTIVE)
    @Pattern(regexp = PATTERN_ACTIVE, message = PATTERN_ACTIVE_MESSAGE)
    private String active;
}
