package com.ead.config.security.userdetails;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthCurrentUserService {

    public UserDetailsImpl getCurrentUser() {
        return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UUID getCurrentUserId() {
        return this.getCurrentUser().getId();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getAuthorizationByHeader() {
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        if(requestAttributes == null)
            return null;

        final ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;

        return servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    }
}
