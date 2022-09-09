package com.ead.services.usercourse;

import com.ead.assembler.UserCourseModelAssembler;
import com.ead.exceptions.UserBlockedException;
import com.ead.model.UserCourseModel;
import com.ead.model.UserModel;
import com.ead.model.request.usercourse.SubscriptionUserInCurseRequest;
import com.ead.model.response.CourseResponse;
import com.ead.model.response.usercourse.SubscriptionUserInCurseResponse;
import com.ead.repositories.UserCourseRepository;
import com.ead.services.UserByIdOrElseThrowService;
import com.ead.services.courses.CourseByIdOrElseThrowService;
import com.ead.validations.ExistsByUserIdAndCourseIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionUserInCurseService {

    private final UserCourseRepository repository;

    private final UserCourseModelAssembler assembler;

    private final UserByIdOrElseThrowService userByIdOrElseThrowService;

    private final CourseByIdOrElseThrowService courseByIdOrElseThrowService;

    private final ExistsByUserIdAndCourseIdService existsByUserIdAndCourseIdService;

    public SubscriptionUserInCurseResponse call(final UUID userId,
                                                final SubscriptionUserInCurseRequest request) {
        final UserModel user = this.userByIdOrElseThrowService.call(userId);

        if (user.isBlocked())
            throw new UserBlockedException();

        this.existsByUserIdAndCourseIdService.valid(user.getId(), request.getCourseId());

        final CourseResponse course = this.courseByIdOrElseThrowService.call(request.getCourseId());

        final UserCourseModel userCourse = new UserCourseModel(user, course.getId());

        final UserCourseModel userCourseSaved = this.repository.save(userCourse);

        return this.assembler.toResponse(userCourseSaved, course);
    }
}
