package com.ead.resources;

import com.ead.resources.request.UserUpdatePasswordRequest;
import com.ead.resources.response.UserUpdatePasswordResponse;
import com.ead.services.UserUpdatePasswordService;
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
public class UserUpdatePasswordResource {

    private final UserUpdatePasswordService service;

    @PutMapping("/users/{id}/password")
    public ResponseEntity<UserUpdatePasswordResponse> updatePassword(@PathVariable UUID id,
                                                                     @RequestBody UserUpdatePasswordRequest request) {
        final UserUpdatePasswordResponse response = this.service.call(id, request);

        return ResponseEntity.ok(response);
    }
}
