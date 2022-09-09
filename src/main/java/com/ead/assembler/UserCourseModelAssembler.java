package com.ead.assembler;

import com.ead.model.UserCourseModel;
import com.ead.model.response.CourseResponse;
import com.ead.model.response.UserResponse;
import com.ead.model.response.usercourse.SubscriptionUserInCurseResponse;
import org.springframework.stereotype.Component;

@Component
public class UserCourseModelAssembler {

    public SubscriptionUserInCurseResponse toResponse(final UserCourseModel userCourseSaved,
                                                      CourseResponse courseResponse) {

        final UserResponse user = UserResponse.builder()
                                              .id(userCourseSaved.getUser().getId())
                                              .fullName(userCourseSaved.getUser().getFullName())
                                              .build();

        final CourseResponse course = CourseResponse.builder()
                                                    .id(courseResponse.getId())
                                                    .description(courseResponse.getName())
                                                    .build();

        return SubscriptionUserInCurseResponse.builder()
                                              .id(userCourseSaved.getId())
                                              .course(course)
                                              .user(user)
                                              .build();
    }
}
