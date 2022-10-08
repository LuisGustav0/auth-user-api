package com.ead.resources.users;

import com.ead.model.filter.UserFilter;
import com.ead.model.response.PageUserResponse;
import com.ead.services.PageableUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageableUserResource {

    private final PageableUserService service;

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<PageUserResponse> call(final UserFilter filter,
                                                 final @PageableDefault(
                                                         sort = "createdAt",
                                                         direction = Sort.Direction.DESC
                                                 ) Pageable pageable) {
        final PageUserResponse response = this.service.call(filter, pageable);

        return ResponseEntity.ok(response);
    }
}
