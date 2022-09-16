package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class UserCourseByCourseIdNotFoundException extends ApmException {

    public UserCourseByCourseIdNotFoundException() {
        super(ErrorType.USER_COURSE_BY_COURSE_ID_NOT_FOUND.getMessage(), ErrorType.USER_COURSE_BY_COURSE_ID_NOT_FOUND.toString());
    }
}
