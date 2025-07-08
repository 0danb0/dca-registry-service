package com.danb.dca.registry_service.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RegistryDTO {
    private String userUuid;
    private String applicationId;
    private String email;
    private String active;
    private List<String> roles;
    private String lastAccessDate;
}
