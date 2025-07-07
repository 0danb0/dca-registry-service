package com.danb.dca.registry_service.services;

import com.danb.dca.registry_service.exceptions.RegistryException;
import com.danb.dca.registry_service.mappers.DtoMapper;
import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.po.RegistryPO;
import com.danb.dca.registry_service.models.response.InternalRegistryDeleteResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryInsertResponse;
import com.danb.dca.registry_service.models.response.InternalRegistryUpdateResponse;
import com.danb.dca.registry_service.repositories.RegistryRepository;
import com.danb.dca.registry_service.utils.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalService {

    private final Tools tools;
    private final RegistryRepository registryRepository;
    private final DtoMapper dtoMapper;

    public InternalRegistryUpdateResponse updateRegistryEntry(RegistryDTO registryDTO) throws RegistryException {
        log.info("-- Internal Service - updateRegistryEntry - START");

        Pair<String, String> keysPair = tools.generatePkSk(registryDTO);
        RegistryPO existingEntry = registryRepository.checkUserIsPresentAndRetrieve(keysPair.getLeft(), keysPair.getRight());
        registryRepository.delete(existingEntry);

        RegistryPO newEntry = dtoMapper.fromRegistryDtoToPO(registryDTO, keysPair.getLeft(), keysPair.getRight());
        registryRepository.insert(newEntry);

        log.info("-- Internal Service - updateRegistryEntry - DONE");
        return InternalRegistryUpdateResponse.builder().build();
    }

    public InternalRegistryInsertResponse insertRegistryEntry(RegistryDTO registryDTO) throws RegistryException {
        log.info("-- Internal Service - insertRegistryEntry - START");

        Pair<String, String> keysPair = tools.generatePkSk(registryDTO);
        registryRepository.checkUserIsPresent(keysPair.getLeft(), keysPair.getRight());

        RegistryPO registryPO = dtoMapper.fromRegistryDtoToPO(registryDTO, keysPair.getLeft(), keysPair.getRight());
        registryRepository.insert(registryPO);

        log.info("-- Internal Service - insertRegistryEntry - DONE");
        return InternalRegistryInsertResponse.builder().build();
    }

    public InternalRegistryDeleteResponse deleteRegistryEntry(RegistryDTO registryDTO) throws RegistryException {
        log.info("-- Internal Service - deleteRegistryEntry - START");

        Pair<String, String> keysPair = tools.generatePkSk(registryDTO);
        registryRepository.checkUserIsPresent(keysPair.getLeft(), keysPair.getRight());

        RegistryPO registryPO = dtoMapper.fromRegistryDtoToPO(registryDTO, keysPair.getLeft(), keysPair.getRight());
        registryRepository.delete(registryPO);

        log.info("-- Internal Service - deleteRegistryEntry - DONE");
        return InternalRegistryDeleteResponse.builder().build();
    }

}
