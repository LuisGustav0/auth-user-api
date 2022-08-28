package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class LoginIsAlreadyBeingUsedException extends ApmException {

    public LoginIsAlreadyBeingUsedException() {
        super(ErrorType.LOGIN_IS_ALREADY_BEING_USED.getMessage(), ErrorType.LOGIN_IS_ALREADY_BEING_USED.toString());
    }
}
