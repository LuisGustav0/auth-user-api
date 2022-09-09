package com.ead.resources.usercourse;

import com.ead.model.response.PageableUserCourseResponse;
import com.ead.services.PageableUserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class PageableUserCourseResource {

    private final PageableUserCourseService service;

    @GetMapping("/users/{userId}/courses")
    public ResponseEntity<PageableUserCourseResponse> call(@PathVariable UUID userId,
                                                           final @PageableDefault(
                                                                   sort = "createdAt",
                                                                   direction = Sort.Direction.DESC
                                                           ) Pageable pageable) {
        final PageableUserCourseResponse response = this.service.call(userId, pageable);

        return ResponseEntity.ok(response);
    }
}
