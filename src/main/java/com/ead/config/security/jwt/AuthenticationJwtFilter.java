package com.ead.config.security.jwt;

import com.ead.config.security.userdetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthenticationJwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final UserDetailsServiceImpl userDetailsService;

    private String getTokenHeader(final HttpServletRequest request) {
        final String headerAuth = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isNotEmpty(headerAuth) && headerAuth.startsWith("Bearer "))
            return headerAuth.substring(7);

        return null;
    }

    private void authenticationJwt(final HttpServletRequest request) {
        try {
            final String token = this.getTokenHeader(request);

            if (StringUtils.isEmpty(token))
                return;

            if (this.jwtProvider.notValidateJwt(token))
                return;

            final String username = this.jwtProvider.getUsernameJwt(token);

            final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            final var authentication = new UsernamePasswordAuthenticationToken(userDetails,
                                                                               null,
                                                                               userDetails.getAuthorities());

            final WebAuthenticationDetails details = new WebAuthenticationDetailsSource().buildDetails(request);
            authentication.setDetails(details);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception ex) {
            log.error("Cannot set User Authentication: {}", ex);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        this.authenticationJwt(request);

        filterChain.doFilter(request, response);
    }
}
