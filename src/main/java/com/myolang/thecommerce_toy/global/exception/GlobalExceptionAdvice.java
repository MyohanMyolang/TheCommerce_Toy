package com.myolang.thecommerce_toy.global.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionAdvice {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> validateError(MethodArgumentNotValidException e){
    Map<String, String> resultMap = new HashMap<>();

    for(FieldError error : e.getBindingResult().getFieldErrors()){
      resultMap.put(error.getField(), error.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }
}
