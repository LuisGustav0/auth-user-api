package com.ead.assembler;

import com.ead.model.response.PageableCourseResponse;
import com.ead.model.response.PageableUserCourseResponse;
import org.springframework.stereotype.Component;

@Component
public class PageableCourseResponseAssembler {

    public PageableUserCourseResponse toResponse(final PageableCourseResponse response) {
        return PageableUserCourseResponse.builder()
                                         .pageNumber(response.getPageNumber())
                                         .pageSize(response.getPageSize())
                                         .totalPages(response.getTotalPages())
                                         .totalElements(response.getTotalElements())
                                         .data(response.getData())
                                         .build();
    }
}
