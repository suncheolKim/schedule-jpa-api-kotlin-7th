package net.sckim.schedule.api.domain.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> "[" + fieldError.getField() + " : " + fieldError.getDefaultMessage() + "]")
                .collect(Collectors.joining("\n"));

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
