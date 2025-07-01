package com.danb.dca.registry_service.services;

import com.danb.dca.registry_service.enums.DomainMsg;
import com.danb.dca.registry_service.enums.ErrorMsg;
import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.response.RegistryAuthResponse;
import com.danb.dca.registry_service.repositories.RegistryRepository;
import com.danb.dca.registry_service.helper.AuthHelper;
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

        String authToken = "";
        if(registryRepository.canLogin(pk)) {
            authToken = authHelper.createUserToken(registryDTO.getEmail(), registryDTO.getApplicationId(), "true");
            registryRepository.updateLastAccessDate(pk);
        }

        if(authToken.isEmpty()){
            throw new RegistryException(
                    ErrorMsg.DCA_RGT_SRV_04.getCode(),
                    ErrorMsg.DCA_RGT_SRV_04.getMessage(),
                    DomainMsg.REGISTRY_SERVICE_TECHNICAL.getName(),
                    ErrorMsg.DCA_RGT_SRV_04.getCode()
            );
        }

        log.info("-- Auth Service - DONE");
        return RegistryAuthResponse.builder().token(authToken).build();
    }
}
