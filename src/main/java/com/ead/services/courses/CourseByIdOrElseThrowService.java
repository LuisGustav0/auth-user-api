package com.ead.services.courses;

import com.ead.clients.courseapi.CourseByIdOrElseThrowClientApi;
import com.ead.exceptions.UserNotFoundException;
import com.ead.model.response.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseByIdOrElseThrowService {

    private final CourseByIdOrElseThrowClientApi courseByIdOrElseThrowClientApi;

    public CourseResponse call(final UUID courseId) {
        final CourseResponse course = this.courseByIdOrElseThrowClientApi.call(courseId);

        if (course == null)
            throw new UserNotFoundException();

        return course;
    }
}
