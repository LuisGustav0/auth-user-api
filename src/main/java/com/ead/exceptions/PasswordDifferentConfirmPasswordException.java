package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class PasswordDifferentConfirmPasswordException extends ApmException {

    public PasswordDifferentConfirmPasswordException() {
        super(ErrorType.PASSWORD_DIFFERENT_CONFIRM_PASSWORD_REQUIRED.getMessage(),
              ErrorType.PASSWORD_DIFFERENT_CONFIRM_PASSWORD_REQUIRED.toString());
    }
}
