package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class PasswordRequiredException extends ApmException {

    public PasswordRequiredException() {
        super(ErrorType.PASSWORD_REQUIRED.getMessage(), ErrorType.PASSWORD_REQUIRED.toString());
    }
}
