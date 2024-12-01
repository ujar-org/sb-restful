package com.iqkv.boot.mvc.rest;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultRestfulErrorHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiError> handleException(ResourceNotFoundException e,
                                                  HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        e.getMessage(),
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now()
    );

    return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleException(Exception e,
                                                  HttpServletRequest request) {
    ApiError apiError = new ApiError(
        request.getRequestURI(),
        e.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        LocalDateTime.now()
    );

    return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
