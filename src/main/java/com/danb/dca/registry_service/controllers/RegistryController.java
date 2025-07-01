package com.danb.dca.registry_service.controllers;

import com.danb.dca.registry_service.models.request.RegistryAuthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_KEY_NAME_STRING;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegistryController {

    @PostMapping(value = "/registry/auth")
    public ResponseEntity<Object> auth(@RequestHeader(HEADER_APP_KEY_NAME_STRING) String appHeader, @RequestBody RegistryAuthRequest registryAuthRequest){
        log.info("- Auth - START");
        log.info("- Auth - END");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
