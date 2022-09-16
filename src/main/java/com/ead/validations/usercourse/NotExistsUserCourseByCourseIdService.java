package com.ead.validations.usercourse;

import com.ead.exceptions.UserCourseByCourseIdNotFoundException;
import com.ead.repositories.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotExistsUserCourseByCourseIdService {

    private final UserCourseRepository repository;

    public void valid(final UUID courseId) {
        if (!this.repository.existsByCourseId(courseId))
            throw new UserCourseByCourseIdNotFoundException();
    }
}
