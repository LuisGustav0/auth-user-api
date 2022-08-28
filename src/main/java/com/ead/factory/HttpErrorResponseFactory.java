package com.ead.factory;

import com.ead.model.http.HttpErrorResponse;

public class HttpErrorResponseFactory {

    private HttpErrorResponseFactory() {
    }

    public static HttpErrorResponse build(String errorCode) {
        return HttpErrorResponse.builder().errorCode(errorCode).build();
    }

    public static HttpErrorResponse build(String errorCode, String message) {
        return HttpErrorResponse.builder().errorCode(errorCode).message(message).build();
    }
}
