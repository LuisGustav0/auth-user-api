package com.ead.resources.auth;

import com.ead.model.request.UserCreatedRequest;
import com.ead.model.response.UserResponse;
import com.ead.services.CreateUserAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
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
public class AuthSignupAdminUserResource {

    private final CreateUserAdminService service;

    @PostMapping("/auth/signup/admin/user")
    public ResponseEntity<UserResponse> call(@RequestBody @Valid UserCreatedRequest request) {
        log.info("POST AuthSignupAdminUserResource.call Request: {}", request);

        final UserResponse response = this.service.call(request);

        log.info("POST AuthSignupAdminUserResource.call Response: {}", response);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
