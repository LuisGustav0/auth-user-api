package com.ead.resources.users;

import com.ead.model.request.UserUpdateRequest;
import com.ead.model.response.UserResponse;
import com.ead.services.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UpdateUserResource {

    private final UpdateUserService service;

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable UUID id,
                                               @RequestBody UserUpdateRequest request) {
        final UserResponse response = this.service.call(id, request);

        return ResponseEntity.ok(response);
    }
}
