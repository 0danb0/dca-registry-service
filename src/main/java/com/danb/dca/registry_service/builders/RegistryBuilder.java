package com.danb.dca.registry_service.builders;

import com.danb.dca.registry_service.models.dto.RegistryDTO;
import com.danb.dca.registry_service.models.po.RegistryPO;
import com.danb.dca.registry_service.utils.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistryBuilder {

    private final Tools tools;

    public RegistryPO fromDtoToPo(RegistryDTO registryDTO){
//        String pk = tools.createPk(invoiceDto.getInvoiceNumber());
//        String sk = tools.getInstant();
        return null;
    }
}
