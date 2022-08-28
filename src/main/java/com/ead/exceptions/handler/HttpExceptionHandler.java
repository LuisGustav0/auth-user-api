package com.ead.exceptions.handler;

import com.ead.exceptions.*;
import com.ead.factory.HttpErrorResponseFactory;
import com.ead.model.http.HttpErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(LoginIsAlreadyBeingUsedException.class)
    public ResponseEntity<HttpErrorResponse> handleLoginIsAlreadyBeingUsedException(LoginIsAlreadyBeingUsedException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(EmailIsAlreadyBeingUsedException.class)
    public ResponseEntity<HttpErrorResponse> handleEmailIsAlreadyBeingUsedException(EmailIsAlreadyBeingUsedException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(PasswordRequiredException.class)
    public ResponseEntity<HttpErrorResponse> handlePasswordRequiredException(PasswordRequiredException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(ConfirmPasswordRequiredException.class)
    public ResponseEntity<HttpErrorResponse> handleConfirmPasswordRequiredException(ConfirmPasswordRequiredException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(PasswordDifferentConfirmPasswordException.class)
    public ResponseEntity<HttpErrorResponse> handlePasswordDifferentConfirmPasswordException(PasswordDifferentConfirmPasswordException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }
}
