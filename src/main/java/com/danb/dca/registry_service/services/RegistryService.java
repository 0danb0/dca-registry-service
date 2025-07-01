package com.danb.dca.registry_service.services;

import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.response.RegistryAuthResponse;
import com.danb.dca.registry_service.repositories.RegistryRepository;
import com.danb.dca.registry_service.utils.AuthHelper;
import com.danb.dca.registry_service.utils.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistryService {

    private final Tools tools;
    private final AuthHelper authHelper;
    private final RegistryRepository registryRepository;

    public RegistryAuthResponse auth(RegistryDTO registryDTO) throws RegistryException {
        log.info("-- Auth Service - START");

        String user = tools.extractUserFromEmail(registryDTO.getEmail());
        String pk = tools.createPk(user);
        registryRepository.isRegistryDtoPresentByPk(pk);

        String authToken = authHelper.createUserToken(registryDTO.getEmail(),registryDTO.getApplicationId());

        log.info("-- Auth Service - DONE");
        return RegistryAuthResponse.builder().token(authToken).build();
    }
}
