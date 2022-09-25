package com.ead.resources.usercourse;

import com.ead.model.response.usercourse.PageableUserCourseResponse;
import com.ead.services.usercourse.PageableUserCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Log4j2
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

        log.info("PageableUserCourseResource.call Response: {}", response);

        return ResponseEntity.ok(null);
    }
}
