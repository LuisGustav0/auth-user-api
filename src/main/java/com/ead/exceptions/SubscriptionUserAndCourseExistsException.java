package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class SubscriptionUserAndCourseExistsException extends ApmException {

    public SubscriptionUserAndCourseExistsException() {
        super(ErrorType.SUBSCRIPTION_USER_COURSE_AND_EXISTS_ERROR.getMessage(), ErrorType.SUBSCRIPTION_USER_COURSE_AND_EXISTS_ERROR.toString());
    }
}
