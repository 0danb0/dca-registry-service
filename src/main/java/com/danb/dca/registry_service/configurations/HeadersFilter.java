package com.danb.dca.registry_service.configurations;

import com.danb.dca.registry_service.properties.SecurityProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_KEY_NAME_STRING;

@Slf4j
@Component
@RequiredArgsConstructor
public class HeadersFilter extends OncePerRequestFilter {

    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String appKey = request.getHeader(HEADER_APP_KEY_NAME_STRING);

        if (appKey == null || !securityProperties.getLicensedApps().contains(appKey)) {
            log.error("Internal filter - Header retrieved is invalid -> {}", appKey);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        log.debug("Internal filter - Header retrieved from request -> {}", appKey);
        filterChain.doFilter(request, response);
    }
}
