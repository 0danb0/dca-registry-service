package com.danb.dca.registry_service.services;

import com.danb.dca.registry_service.repositories.RegistryRepository;
import com.danb.dca.registry_service.helper.AuthHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InternalService {

    private final AuthHelper authHelper;
    private final RegistryRepository registryRepository;
}
