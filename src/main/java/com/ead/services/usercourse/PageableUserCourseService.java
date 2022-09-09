package com.ead.services.usercourse;

import com.ead.assembler.PageableCourseResponseAssembler;
import com.ead.clients.courseapi.PageableCourseClientApi;
import com.ead.model.response.PageableCourseResponse;
import com.ead.model.response.usercourse.PageableUserCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PageableUserCourseService {

    private final PageableCourseClientApi pageableCourseClientApi;

    private final PageableCourseResponseAssembler assembler;

    public PageableUserCourseResponse call(final UUID userId,
                                           final Pageable pageable) {
        final PageableCourseResponse response = this.pageableCourseClientApi.call(userId, pageable);

        return this.assembler.toResponse(response);
    }
}
