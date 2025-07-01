package com.danb.dca.registry_service.mappers;

import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.po.RegistryPO;
import com.danb.dca.registry_service.models.request.RegistryAuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, java.time.Instant.class})
public interface RequestsMapper {

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "active", expression = "java(\"false\")")
    RegistryDTO fromAuthRequestToRegistryDto(RegistryAuthRequest registryAuthRequest);

    @Mapping(target = "pk", expression = "java(pk)")
    @Mapping(target = "sk", expression = "java(sk)")
    @Mapping(target = "updateDate", expression = "java(Instant.now().toString())")
    @Mapping(target = "creationDate", expression = "java(Instant.now().toString())")
    RegistryPO fromRegistryDtoToPO(RegistryDTO registryDTO, String pk, String sk);
}
