package com.ead.services.auth;

import com.ead.config.security.jwt.JwtProvider;
import com.ead.model.request.auth.LoginRequest;
import com.ead.model.response.auth.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthLoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public JwtResponse call(final LoginRequest request) {
        final var authenticationToken = new UsernamePasswordAuthenticationToken(request.getLogin(),
                                                                                request.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = this.jwtProvider.generateJwt(authentication);

        return new JwtResponse(jwt);
    }
}
