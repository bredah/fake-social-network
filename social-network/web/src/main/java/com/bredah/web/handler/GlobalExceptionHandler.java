package com.bredah.web.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bredah.web.exception.UsuarioExistenteException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UsuarioExistenteException.class)
  public ResponseEntity<String> handleUsuarioExistenteException(UsuarioExistenteException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<String> handleConstraintViolationException(
      DataIntegrityViolationException ex) {
    if (ex.getCause() instanceof ConstraintViolationException) {
      ConstraintViolationException constraintViolationException =
          (ConstraintViolationException) ex.getCause();
      Set<ConstraintViolation<?>> violations =
          constraintViolationException.getConstraintViolations();
      List<String> messages = new ArrayList<>();
      for (ConstraintViolation<?> violation : violations) {
        messages.add(violation.getMessage());
      }
      String errorMessage = "Validation failed: " + String.join(", ", messages);
      return ResponseEntity.badRequest().body(errorMessage);
    }
    // Handle other exceptions if needed
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }
}
