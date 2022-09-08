package com.ead.resources.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableUserCourseResponse {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    private List<CourseResponse> data;
}
