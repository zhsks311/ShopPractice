package com.shop.display.interfaces.exception;

import com.shop.display.interfaces.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST.value(), ex.getMessage()),
                                    BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "Internal server error occurred: " + ex.getMessage()),
                                    INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "Null pointer exception occurred: " + ex.getMessage()),
                                    INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "An error occurred: " + ex.getMessage()),
                                    INTERNAL_SERVER_ERROR);
    }


}
