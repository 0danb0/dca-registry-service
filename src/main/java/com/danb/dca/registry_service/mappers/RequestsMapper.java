package com.danb.dca.registry_service.mappers;

import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.request.RegistryAuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, java.time.Instant.class})
public interface RequestsMapper {

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "active", expression = "java(\"false\")")
    RegistryDTO fromAuthRequestToRegistryDto(RegistryAuthRequest registryAuthRequest);
}
