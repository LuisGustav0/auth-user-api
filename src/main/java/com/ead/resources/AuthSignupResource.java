package com.ead.resources;

import com.ead.resources.request.UserCreatedRequest;
import com.ead.resources.response.UserResponse;
import com.ead.services.UserCreatedService;
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
public class AuthSignupResource {

    private final UserCreatedService service;

    @PostMapping("/auth/signup")
    public ResponseEntity<UserResponse> call(@RequestBody @Valid UserCreatedRequest request) {
        log.info("POST AuthSignupResource.call Request: {}", request);

        final UserResponse response = this.service.call(request);

        log.info("POST AuthSignupResource.call Response: {}", response);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }
}
