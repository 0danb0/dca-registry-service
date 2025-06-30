package com.danb.dca.registry_service.builders;

import com.danb.dca.registry_service.models.response.GenericHealthCheckResponse;
import com.danb.dca.registry_service.utils.Tools;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GenericBuilder {

    private final Tools tools;

    public GenericHealthCheckResponse genericHealthCheckResponse(String domain, String name){
        GenericHealthCheckResponse genericHealthCheckResponse = new GenericHealthCheckResponse();
        genericHealthCheckResponse.setTimestamp(tools.getInstant());
        genericHealthCheckResponse.setMessage("Online and Reachable");
        genericHealthCheckResponse.setDetailed(String.join(" ",name,"service is Online and Reachable"));
        genericHealthCheckResponse.setDomain(domain);
        return  genericHealthCheckResponse;
    }

}
