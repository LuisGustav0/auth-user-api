package com.ead.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageableCourseResponse {

    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long totalElements;

    private List<CourseResponse> data;
}
