package com.ead.exceptions.handler;

import com.ead.enums.ErrorType;
import com.ead.exceptions.ConfirmPasswordRequiredException;
import com.ead.exceptions.EmailIsAlreadyBeingUsedException;
import com.ead.exceptions.LoginIsAlreadyBeingUsedException;
import com.ead.exceptions.PasswordDifferentConfirmPasswordException;
import com.ead.exceptions.PasswordRequiredException;
import com.ead.exceptions.ServiceCourseUnavailableException;
import com.ead.exceptions.SubscriptionUserAndCourseExistsException;
import com.ead.exceptions.UnexpectedErrorException;
import com.ead.exceptions.UserBlockedException;
import com.ead.exceptions.UserCourseByCourseIdNotFoundException;
import com.ead.exceptions.UserNotFoundException;
import com.ead.factory.HttpErrorResponseFactory;
import com.ead.model.http.HttpErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Slf4j
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        log.error(ex.getMessage());

        BindingResult result = ex.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        final HttpErrorResponse httpErrorResponse = HttpErrorResponseFactory.build(ErrorType.METHOD_ARG_NOT_VALID_ERROR.toString(),
                                                                                   ErrorType.METHOD_ARG_NOT_VALID_ERROR.getMessage());
        for (FieldError fieldError : fieldErrors) {
            httpErrorResponse.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(httpErrorResponse);
    }

    @ExceptionHandler(ServiceCourseUnavailableException.class)
    public ResponseEntity<HttpErrorResponse> handleServiceCourseUnavailableException(ServiceCourseUnavailableException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(UnexpectedErrorException.class)
    public ResponseEntity<HttpErrorResponse> handleUnexpectedErrorException(UnexpectedErrorException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(UserBlockedException.class)
    public ResponseEntity<HttpErrorResponse> handleUserBlockedException(UserBlockedException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
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

    @ExceptionHandler(SubscriptionUserAndCourseExistsException.class)
    public ResponseEntity<HttpErrorResponse> handleSubscriptionUserAndCourseExistsException(SubscriptionUserAndCourseExistsException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }

    @ExceptionHandler(UserCourseByCourseIdNotFoundException.class)
    public ResponseEntity<HttpErrorResponse> handleUserCourseByCourseIdNotFoundException(UserCourseByCourseIdNotFoundException ex) {
        logger.error(ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(HttpErrorResponseFactory.build(ex.getErrorCode(), ex.getMessage()));
    }
}
