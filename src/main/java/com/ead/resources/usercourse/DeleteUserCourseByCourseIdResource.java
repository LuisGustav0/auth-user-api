package com.ead.resources.usercourse;

import com.ead.model.response.usercourse.DeleteUserCourseResponse;
import com.ead.services.usercourse.DeleteUserCourseByCourseIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class DeleteUserCourseByCourseIdResource {

    private final DeleteUserCourseByCourseIdService service;

    @DeleteMapping("/users/courses/{courseId}")
    public ResponseEntity<DeleteUserCourseResponse> call(@PathVariable UUID courseId) {
        final DeleteUserCourseResponse response = this.service.call(courseId);

        return ResponseEntity.ok(response);
    }
}
