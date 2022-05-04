package me.wup.blog.controller.exceptions;

import lombok.Builder;
import me.wup.blog.services.exceptions.ResourceNotFoundException;
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
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError error = new ValidationError();
        error.setTimestamp(Instant.now());
        error.setStatus(httpStatus.value());
        error.setError("Validation exception!");
        error.setMessage(e.getMessage());
        error.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
            return ResponseEntity.status(httpStatus).body(error);
        }
    }

