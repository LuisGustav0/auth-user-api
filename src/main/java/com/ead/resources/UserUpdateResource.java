package com.ead.resources;

import com.ead.resources.request.UserUpdateRequest;
import com.ead.resources.response.UserResponse;
import com.ead.services.UserUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserUpdateResource {

    private final UserUpdateService service;

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id,
                                               @RequestBody UserUpdateRequest request) {
        final UserResponse response = this.service.call(id, request);

        return ResponseEntity.ok(response);
    }
}
