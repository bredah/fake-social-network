package com.bredah.web.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bredah.web.exception.UsuarioExistenteException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(UsuarioExistenteException.class)
  public ResponseEntity<String> handleUsuarioExistenteException(UsuarioExistenteException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}
