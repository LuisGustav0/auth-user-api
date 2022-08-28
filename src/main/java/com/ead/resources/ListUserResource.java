package com.ead.resources;

import com.ead.resources.response.PageUserResponse;
import com.ead.services.ListUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ListUserResource {

    private final ListUserService service;

    @GetMapping("/users")
    public ResponseEntity<PageUserResponse> getListUser(final Pageable pageable) {
        final PageUserResponse response = this.service.call(pageable);

        return ResponseEntity.ok(response);
    }
}
