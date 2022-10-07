package com.ead.resources.auth;

import com.ead.model.request.auth.LoginRequest;
import com.ead.model.response.auth.JwtResponse;
import com.ead.services.auth.AuthLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthLoginResource {

    private final AuthLoginService service;

    @PostMapping("/auth/login")
    public ResponseEntity<JwtResponse> call(@RequestBody @Valid LoginRequest request) {
        final JwtResponse response = this.service.call(request);

        return ResponseEntity.ok(response);
    }
}
