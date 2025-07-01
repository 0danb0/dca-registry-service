package com.danb.dca.registry_service.models.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class InternalRegistryInsertResponse extends RestResponse {
}