package com.danb.dca.registry_service.services;

import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.response.InternalRegistryDeleteResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryInsertResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryUpdateResponse;
import com.danb.dca.registry_service.repositories.RegistryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalService {

    private final RegistryRepository registryRepository;
    
    public InternalRegistryUpdateResponse updateRegistryEntry(RegistryDTO registryDTO){
        log.info("- Internal Service - updateRegistryEntry - START");

        log.info("- Internal Service - updateRegistryEntry - DONE");
        return InternalRegistryUpdateResponse.builder().build();
    }
    
    public InternalRegistryInsertResponse insertRegistryEntry(RegistryDTO registryDTO){
        log.info("- Internal Service - insertRegistryEntry - START");

        //pk con nome applicazione sk con nome recuperato da email

        log.info("- Internal Service - insertRegistryEntry - DONE");
        return InternalRegistryInsertResponse.builder().build();
    }
    
    public InternalRegistryDeleteResponse deleteRegistryEntry(RegistryDTO registryDTO){
        log.info("- Internal Service - deleteRegistryEntry - START");

        log.info("- Internal Service - deleteRegistryEntry - DONE");
        return InternalRegistryDeleteResponse.builder().build();
    }
}
