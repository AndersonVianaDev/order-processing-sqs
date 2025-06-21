package com.anderson.product_api.controller;

import com.anderson.product_api.infra.exception.DataConflictException;
import com.anderson.product_api.infra.exception.InvalidStockOperationException;
import com.anderson.product_api.infra.exception.NotFoundException;
import com.anderson.product_api.infra.exception.StandardException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFound(NotFoundException e, HttpServletRequest request) {
        final StandardException err = buildStandard(
                "Not found",
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(err.status()).body(err);
    }

    @ExceptionHandler(DataConflictException.class)
    public ResponseEntity<StandardException> dataConflict(DataConflictException e, HttpServletRequest request) {
        final StandardException err = buildStandard(
                "Data conflict",
                HttpStatus.CONFLICT,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(err.status()).body(err);
    }

    @ExceptionHandler(InvalidStockOperationException.class)
    public ResponseEntity<StandardException> invalidStockOperation(InvalidStockOperationException e,
                                                                   HttpServletRequest request) {
        final StandardException err = buildStandard(
              "Invalid stock operation",
                HttpStatus.UNPROCESSABLE_ENTITY,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(err.status()).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardException> unexpectedException(Exception e, HttpServletRequest request) {
        final StandardException err = buildStandard(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(err.status()).body(err);
    }

    private StandardException buildStandard(String error, HttpStatus status, String message, String path) {
         return StandardException.from(
                LocalDateTime.now(),
                status.value(),
                error,
                message,
                path
        );
    }

}
