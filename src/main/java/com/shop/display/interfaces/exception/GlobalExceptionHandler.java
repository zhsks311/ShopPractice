package com.shop.display.interfaces.exception;

import com.shop.display.interfaces.model.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GlobalExceptionHandler {
    // TODO(jaehwi) - 로그 시스템 추가

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST.value(),
                                                          message.substring(0, Math.min(message.length(), 50))),
                                    BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "Internal server error occurred: "
                                                          + message.substring(0, Math.min(message.length(), 50))),
                                    INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionResponse> handleNullPointerException(NullPointerException ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "Null pointer exception occurred: "
                                                          + message.substring(0, Math.min(message.length(), 50))),
                                    INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodValidation(MethodArgumentNotValidException ex) {
        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> message.append(error.getField())
                                                                       .append(" ")
                                                                       .append(error.getDefaultMessage())
                                                                       .append(" "));
        return new ResponseEntity<>(new ExceptionResponse(BAD_REQUEST.value(),
                                                          "Your Request is not valid: "
                                                          + message.substring(0, Math.min(message.length(), 50))),
                                    BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {
        String message = ex.getMessage();
        return new ResponseEntity<>(new ExceptionResponse(INTERNAL_SERVER_ERROR.value(),
                                                          "An error occurred: "
                                                          + message.substring(0, Math.min(message.length(), 50))),
                                    INTERNAL_SERVER_ERROR);
    }


}
