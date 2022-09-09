package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class UnexpectedErrorException extends ApmException {

    public UnexpectedErrorException(Exception ex) {
        super(ErrorType.UNEXPECTED_ERROR.getMessage(), ErrorType.UNEXPECTED_ERROR.toString(), ex);
    }
}
