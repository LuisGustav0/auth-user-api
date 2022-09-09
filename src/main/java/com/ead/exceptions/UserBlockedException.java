package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class UserBlockedException extends ApmException{

    public UserBlockedException() {
        super(ErrorType.USER_BLOCKED.getMessage(), ErrorType.USER_BLOCKED.toString());
    }
}
