package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class ConfirmPasswordRequiredException extends ApmException {

    public ConfirmPasswordRequiredException() {
        super(ErrorType.CONFIRM_PASSWORD_REQUIRED.getMessage(), ErrorType.CONFIRM_PASSWORD_REQUIRED.toString());
    }
}
