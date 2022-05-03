package me.wup.blog.controller.exceptions;

import lombok.Builder;
import me.wup.blog.services.exceptions.DatabaseException;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Builder
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound (ResourceNotFoundException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        StandardError error = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(httpStatus.value())
                .error("Resource not found!")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();

        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database (DatabaseException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(httpStatus.value())
                .error("Database Exception")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation (MethodArgumentNotValidException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        StandardError error = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(httpStatus.value())
                .error("Validation exception error!")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        for (FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation (DataIntegrityViolationException e, HttpServletRequest request){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        StandardError error = new StandardError.StandardErrorBuilder()
                .timestamp(Instant.now())
                .status(httpStatus.value())
                .error("Data integrity violation! Email or Nickname already registered. ")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(httpStatus).body(error);
    }

}
