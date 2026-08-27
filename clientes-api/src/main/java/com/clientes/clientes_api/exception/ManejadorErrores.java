package com.clientes.clientes_api.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ManejadorErrores {
    @ExceptionHandler(ResourceNotFound.class)
    public ProblemDetail handleResourceNotFound(ResourceNotFound ex){
        ProblemDetail detalleProblema = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detalleProblema.setDetail("Recurso no Encontrado");
        detalleProblema.setType(URI.create("/errors/not-found"));
        return detalleProblema;
    }

    @ExceptionHandler(BadRequest.class)
    public ProblemDetail handleBadRequest(BadRequest ex){
        ProblemDetail problemadetalle = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemadetalle.setDetail("Solicitud incorrecta");
        problemadetalle.setType(URI.create("/errors/bad-request"));
        return problemadetalle;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException ex) {
        ProblemDetail problema = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "La solicitud contiene parámetros inválidos"
        );

        problema.setTitle("Error de Validación");
        problema.setType(URI.create("https://api.clientes.com/errors/validation"));

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        problema.setProperty("invalidParams", errors);

        return problema;
    }
}