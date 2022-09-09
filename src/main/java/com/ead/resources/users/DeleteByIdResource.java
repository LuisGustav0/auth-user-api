package com.ead.resources.users;

import com.ead.model.response.DeleteUserResponse;
import com.ead.services.DeleteByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class DeleteByIdResource {

    private final DeleteByIdService service;

    @DeleteMapping("/users/{id}")
    public ResponseEntity<DeleteUserResponse> deleteById(@PathVariable UUID id) {
        final DeleteUserResponse response = this.service.call(id);

        return ResponseEntity.ok(response);
    }
}
