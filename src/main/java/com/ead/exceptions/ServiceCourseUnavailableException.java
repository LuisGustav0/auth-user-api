package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class ServiceCourseUnavailableException extends ApmException {

    public ServiceCourseUnavailableException() {
        super(ErrorType.SERVICE_UNAVAILABLE.getMessage(), ErrorType.SERVICE_UNAVAILABLE.toString());
    }
}
