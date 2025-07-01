package com.danb.dca.registry_service.controllers;

import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.mappers.RequestsMapper;
import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.request.InternalRegistryDeleteRequest;
import com.danb.dca.registry_service.models.request.InternalRegistryInsertRequest;
import com.danb.dca.registry_service.models.request.InternalRegistryUpdateRequest;
import com.danb.dca.registry_service.models.response.InternalRegistryDeleteResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryInsertResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryUpdateResponse;
import com.danb.dca.registry_service.services.InternalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_INTERNAL_KEY_NAME_STRING;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal")
public class InternalController {

    private final RequestsMapper requestsMapper;
    private final InternalService internalService;

    @PutMapping("/registry")
    @PreAuthorize("hasAuthority('ROLE_big-mtf-boss-bb')")
    public ResponseEntity<InternalRegistryUpdateResponse> updateRegistry(@RequestHeader(HEADER_APP_INTERNAL_KEY_NAME_STRING) String appHeader,
             @Valid @RequestBody InternalRegistryUpdateRequest internalRegistryUpdateRequest) throws RegistryException {
        log.info("- Internal Controller - updateRegistry - START");

        RegistryDTO registryDTO = requestsMapper.fromIntUpdateRequestToRegistryDto(internalRegistryUpdateRequest);
        InternalRegistryUpdateResponse internalRegistryUpdateResponse = internalService.updateRegistryEntry(registryDTO);

        log.info("- Internal Controller - updateRegistry  - DONE");
        return new ResponseEntity<>(internalRegistryUpdateResponse,HttpStatus.OK);
    }

    @PostMapping("/registry")
    @PreAuthorize("hasAuthority('ROLE_big-mtf-boss-bb')")
    public ResponseEntity<InternalRegistryInsertResponse> insertRegistry(@RequestHeader(HEADER_APP_INTERNAL_KEY_NAME_STRING) String appHeader,
             @Valid @RequestBody InternalRegistryInsertRequest internalRegistryInsertRequest) throws RegistryException {
        log.info("- Internal Controller - insertRegistry - START");

        RegistryDTO registryDTO = requestsMapper.fromIntInsertRequestToRegistryDto(internalRegistryInsertRequest);
        InternalRegistryInsertResponse internalRegistryInsertResponse = internalService.insertRegistryEntry(registryDTO);

        log.info("- Internal Controller - insertRegistry - DONE");
        return new ResponseEntity<>(internalRegistryInsertResponse,HttpStatus.OK);
    }

    @DeleteMapping("/registry")
    @PreAuthorize("hasAuthority('ROLE_big-mtf-boss-bb')")
    public ResponseEntity<InternalRegistryDeleteResponse> deleteRegistry(@RequestHeader(HEADER_APP_INTERNAL_KEY_NAME_STRING) String appHeader,
             @Valid @RequestBody InternalRegistryDeleteRequest internalRegistryDeleteRequest) throws RegistryException {
        log.info("- Internal Controller - deleteRegistry - START");

        RegistryDTO registryDTO = requestsMapper.fromIntDeleteRequestToRegistryDto(internalRegistryDeleteRequest);
        InternalRegistryDeleteResponse internalRegistryDeleteResponse = internalService.deleteRegistryEntry(registryDTO);

        log.info("- Internal Controller - deleteRegistry - DONE");
        return new ResponseEntity<>(internalRegistryDeleteResponse,HttpStatus.OK);
    }

}
