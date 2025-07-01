package com.danb.dca.registry_service.controllers;

import com.danb.dca.registry_service.mappers.RequestsMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/internal")
public class InternalController {

    private final RequestsMapper requestsMapper;

}
