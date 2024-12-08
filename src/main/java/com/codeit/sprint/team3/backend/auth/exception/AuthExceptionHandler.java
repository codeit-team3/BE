package com.codeit.sprint.team3.backend.auth.exception;

import com.codeit.sprint.team3.backend.common.security.ExpiredRefreshTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice(basePackages = "com.codeit.sprint.team3.backend.auth")
public class AuthExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        String message = fieldError.getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "code", "VALIDATION_ERROR",
                        "parameter", fieldError.getField(),
                        "message", message != null? message : "검증 오류"
                ));
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                Map.of(
                        "code", "EMAIL_ALREADY_EXISTS",
                        "parameter", "email",
                        "message", ex.getMessage()
                )
        );
    }

    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<Map<String, String>> handleLoginFailedException(LoginFailedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of(
                        "code", "INVALID_CREDENTIALS",
                        "message", ex.getMessage()
                )
        );
    }

    @ExceptionHandler({InvalidRefreshTokenException.class, ExpiredRefreshTokenException.class})
    public ResponseEntity<Map<String, String>> handleRefreshTokenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                Map.of(
                        "code", "UNAUTHORIZED",
                        "message", e.getMessage()
                )
        );
    }

    @ExceptionHandler({IllegalArgumentException.class, UserNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of(
                        "message", ex.getMessage()
                )
        );
    }


}
