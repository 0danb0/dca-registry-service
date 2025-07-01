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

import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_INTERNAL_KEY_NAME_STRING;
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
        String appInternalKey = request.getHeader(HEADER_APP_INTERNAL_KEY_NAME_STRING);

        // Controlla che sia presente **solo uno** dei due header
        if ((appKey == null && appInternalKey == null) || (appKey != null && appInternalKey != null)) {
            log.error("Internal filter - Headers missing or both present. appKey: {}, appInternalKey: {}", appKey, appInternalKey);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Controlla validità appKey
        if (appKey != null && !securityProperties.getLicensedApps().contains(appKey)) {
            log.error("Internal filter - Invalid appKey header -> {}", appKey);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // Controlla validità appInternalKey
        if (appInternalKey != null && !securityProperties.getLicensedInternalApps().contains(appInternalKey)) {
            log.error("Internal filter - Invalid appInternalKey header -> {}", appInternalKey);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.debug("Internal filter - Header valid");
        filterChain.doFilter(request, response);
    }
}
