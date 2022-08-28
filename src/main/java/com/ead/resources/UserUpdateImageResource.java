package com.ead.resources;

import com.ead.resources.request.UserUpdateImageRequest;
import com.ead.resources.response.UserResponse;
import com.ead.services.UserUpdateImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserUpdateImageResource {

    private final UserUpdateImageService service;

    @PutMapping("/users/{id}/image")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable UUID id,
                                                       @RequestBody UserUpdateImageRequest request) {
        final UserResponse response = this.service.call(id, request);

        return ResponseEntity.ok(response);
    }
}
