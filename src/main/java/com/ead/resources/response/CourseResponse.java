package com.ead.resources.response;

import com.ead.enums.CourseLevelE;
import com.ead.enums.CourseStatusE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {

    private UUID id;
    private String name;
    private String description;
    private CourseStatusE statusE;
    private CourseLevelE levelE;
    private UUID userInstructorId;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;
}
