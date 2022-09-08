package com.ead.exceptions;

import com.ead.enums.ErrorType;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginIsAlreadyBeingUsedException extends ApmException {

    public LoginIsAlreadyBeingUsedException(final String username) {
        super(ErrorType.LOGIN_IS_ALREADY_BEING_USED.getMessage(), ErrorType.LOGIN_IS_ALREADY_BEING_USED.toString());
        log.warn("Username {} is Already Taken", username);
    }
}
