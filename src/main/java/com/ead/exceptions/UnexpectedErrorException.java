package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class UnexpectedErrorException extends ApmException {

    public UnexpectedErrorException() {
        super(ErrorType.UNEXPECTED_ERROR.getMessage(), ErrorType.UNEXPECTED_ERROR.toString());
    }
}
