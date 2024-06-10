package br.gov.ma.tce.dscommerce.controllers.handlers;

import br.gov.ma.tce.dscommerce.dto.CustomError;
import br.gov.ma.tce.dscommerce.dto.ValidationError;
import br.gov.ma.tce.dscommerce.services.exceptions.DatabaseException;
import br.gov.ma.tce.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

// Com a annoration @ControllerAdvice, podemos definir traramentos globais para exceções específicas, sem precisar ficar colocando try-catch em várias partes do código
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //config para interceptar esse tipo de exception
    public ResponseEntity<CustomError> resourceNotFound (ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class) //config para interceptar esse tipo de exception
    public ResponseEntity<CustomError> dataBase (DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) //config para interceptar esse tipo de exception
    public ResponseEntity<CustomError> methodArgumentNotValidation (MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
        // for para percorrer todos os erros dentro das lista de exceção e lhe dando o apelodo de f
        for (FieldError f : e.getBindingResult().getFieldErrors()){
            err.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(err);
    }
}
