package com.danb.dca.registry_service.controllers;

import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.mappers.RequestsMapper;
import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.request.RegistryAuthRequest;
import com.danb.dca.registry_service.models.response.RegistryAuthResponse;
import com.danb.dca.registry_service.services.RegistryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_KEY_NAME_STRING;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistryController {

    private final RequestsMapper requestsMapper;
    private final RegistryService registryService;

    @PostMapping(value = "/registry/auth")
    public ResponseEntity<Object> auth(@RequestHeader(HEADER_APP_KEY_NAME_STRING) String appHeader,
            @Valid @RequestBody RegistryAuthRequest registryAuthRequest) throws RegistryException {
        log.info("- Auth - START");

        RegistryDTO registryDTO = requestsMapper.fromAuthRequestToRegistryDto(registryAuthRequest);
        RegistryAuthResponse registryAuthResponse = registryService.auth(registryDTO);

        log.info("- Auth - END");
        return new ResponseEntity<>(registryAuthResponse,HttpStatus.OK);
    }
}
