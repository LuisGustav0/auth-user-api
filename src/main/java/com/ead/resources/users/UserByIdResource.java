package com.ead.resources.users;

import com.ead.model.response.UserResponse;
import com.ead.services.UserByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserByIdResource {

    private final UserByIdService service;

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('STUDENT')")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        final UserResponse response = this.service.call(id);

        return ResponseEntity.ok(response);
    }
}
