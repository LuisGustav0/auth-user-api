package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class EmailIsAlreadyBeingUsedException extends ApmException {

    public EmailIsAlreadyBeingUsedException() {
        super(ErrorType.EMAIL_IS_ALREADY_BEING_USED.getMessage(), ErrorType.EMAIL_IS_ALREADY_BEING_USED.toString());
    }
}
