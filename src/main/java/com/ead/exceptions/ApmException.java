package com.ead.exceptions;

public class ApmException extends RuntimeException {

    protected final String errorCode;

    public ApmException(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}