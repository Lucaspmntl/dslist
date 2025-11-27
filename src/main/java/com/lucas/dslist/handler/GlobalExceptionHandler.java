package com.lucas.dslist.handler;

import com.lucas.dslist.dto.ValidationErrorResponseDTO;
import com.lucas.dslist.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.FieldError;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<String> ResourceNotFoundHandler(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ValidationErrorResponseDTO> MethodArgumentNotValidHandler(MethodArgumentNotValidException exception) {

        ValidationErrorResponseDTO response =
                new ValidationErrorResponseDTO("Erro de validação, verifique os campos enviados.");

        List<FieldError> exceptionField = exception.getBindingResult().getFieldErrors();

        for (FieldError exceptionFields : exceptionField) {
            String field = exceptionFields.getField();
            String message = exceptionFields.getDefaultMessage();
            Object rejectedValue = exceptionFields.getRejectedValue();

            response.addFieldErrors(field, message, rejectedValue);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

