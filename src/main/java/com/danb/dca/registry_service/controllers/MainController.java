package com.danb.dca.registry_service.controllers;

import com.danb.dca.registry_service.builders.GenericBuilder;
import com.danb.dca.registry_service.enums.DomainMsg;
import com.danb.dca.registry_service.models.response.GenericHealthCheckResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final GenericBuilder genericBuilder;

    @GetMapping("/health")
    public ResponseEntity<Object> productHealthCheck() {
        GenericHealthCheckResponse genericHealthCheckResponse = genericBuilder.genericHealthCheckResponse(DomainMsg.API_SERVICE_TECHNICAL.getName(),"Api");
        return new ResponseEntity<>(genericHealthCheckResponse, HttpStatus.OK);
    }
}
