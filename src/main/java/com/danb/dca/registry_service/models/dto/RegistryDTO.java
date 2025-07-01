package com.danb.dca.registry_service.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistryDTO {
    private String userUuid;
    private String applicationId;
    private String email;
    private String active;
}
