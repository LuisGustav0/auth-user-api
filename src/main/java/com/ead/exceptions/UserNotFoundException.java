package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class UserNotFoundException extends ApmException {

    public UserNotFoundException() {
        super(ErrorType.USER_NOT_FOUND.getMessage(), ErrorType.USER_NOT_FOUND.toString());
    }
}
