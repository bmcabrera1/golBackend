package com.example.golbackend.controllerglobal;

import com.example.golbackend.modules.auth.dto.ErrorResponse;
import com.example.golbackend.modules.auth.exception.UserAlredyExists; // Considera renombrar a UserAlreadyExists
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // CORRECCIÓN: Usar el logger de SLF4J, no de java.util.logging
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Manejador para la excepción UserAlredyExists. Devuelve un 409 Conflict.
     * (Sugerencia: Renombrar la excepción a UserAlreadyExistsException)
     */
    @ExceptionHandler(UserAlredyExists.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlredyExists exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Conflict",
                exception.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Manejador para la validación de campos. Devuelve un HTTP 400.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Validation Error");
        body.put("messages", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejador para problemas de conexión con la base de datos (Timeout, no disponible).
     * Devuelve un estado HTTP 503 (Service Unavailable).
     */
    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<ErrorResponse> handleDatabaseConnectionErrors(DataAccessException ex, WebRequest request) {
        log.error("Error de conexión con la base de datos: {}", ex.getRootCause().getMessage());

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.SERVICE_UNAVAILABLE.value(),
                "Service Unavailable",
                "Hubo un problema de comunicación con nuestros servicios. Por favor, inténtelo de nuevo más tarde."
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * Manejador genérico para cualquier otra excepción no controlada.
     * Devuelve un estado HTTP 500 (Internal Server Error).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        log.error("Error no esperado: ", ex);

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "Ocurrió un error inesperado en el servidor."
        );
        return new ResponseEntity<
                >(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}