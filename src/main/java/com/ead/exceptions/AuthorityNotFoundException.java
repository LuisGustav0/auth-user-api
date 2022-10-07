package com.ead.exceptions;

import com.ead.enums.ErrorType;

public class AuthorityNotFoundException extends ApmException {

    public AuthorityNotFoundException() {
        super(ErrorType.AUTHORITY_NOT_FOUND.getMessage(), ErrorType.AUTHORITY_NOT_FOUND.toString());
    }
}
