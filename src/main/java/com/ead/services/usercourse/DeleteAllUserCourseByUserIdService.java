package com.ead.services.usercourse;

import com.ead.model.UserCourseModel;
import com.ead.repositories.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAllUserCourseByUserIdService {

    private final UserCourseRepository repository;

    public boolean call(final UUID userId) {
        final List<UserCourseModel> listUserCourse = this.repository.findAllByUserId(userId);

        if (listUserCourse.isEmpty())
            return false;

        this.repository.deleteAll(listUserCourse);

        return true;
    }
}
