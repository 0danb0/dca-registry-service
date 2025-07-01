package com.danb.dca.registry_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DomainMsg {

    REGISTRY_SERVICE_TECHNICAL("DCA-Registry-Service-Technicals"),
    INTERNAL_CONTROLLER_TECHNICAL("DCA-Internal-Service-Technicals"),
    API_SERVICE_TECHNICAL("DCA-API-Service-Technicals"),
    MICROSERVICE_FUNCTIONAL("MicroServiceFunctional");

    private final String name;
}

