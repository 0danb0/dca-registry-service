package com.danb.dca.registry_service.mappers;

import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.request.InternalRegistryDeleteRequest;
import com.danb.dca.registry_service.models.request.InternalRegistryInsertRequest;
import com.danb.dca.registry_service.models.request.InternalRegistryUpdateRequest;
import com.danb.dca.registry_service.models.request.RegistryAuthRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, java.time.Instant.class})
public interface RequestsMapper {

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "lastAccessDate", expression = "java(Instant.now().toString())")
    RegistryDTO fromAuthRequestToRegistryDto(RegistryAuthRequest registryAuthRequest);

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "lastAccessDate", expression = "java(\"\")")
    RegistryDTO fromIntUpdateRequestToRegistryDto(InternalRegistryUpdateRequest internalRegistryUpdateRequest);

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "lastAccessDate", expression = "java(\"\")")
    RegistryDTO fromIntInsertRequestToRegistryDto(InternalRegistryInsertRequest internalRegistryInsertRequest);

    @Mapping(target = "userUuid", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "active", expression = "java(\"false\")")
    @Mapping(target = "lastAccessDate", expression = "java(\"\")")
    @Mapping(target = "roles", expression = "java(new ArrayList<>())")
    RegistryDTO fromIntDeleteRequestToRegistryDto(InternalRegistryDeleteRequest internalRegistryDeleteRequest);

}
