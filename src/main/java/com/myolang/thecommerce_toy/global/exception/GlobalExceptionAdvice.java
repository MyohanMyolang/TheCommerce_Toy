package com.myolang.thecommerce_toy.global.exception;

import com.myolang.thecommerce_toy.global.exception.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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
@Slf4j
public class GlobalExceptionAdvice {

  @ExceptionHandler(CustomException.class)
  ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
    log.error("Message : " + e.getMessage() + " | "
      + "ErrorCode : " + e.getErrorCode() + " | "
      + "Payload : " + e.getPayload(), e);
    ErrorResponse errorResponse = e.getErrorResponse();
    return ResponseEntity.status(e.getErrorCode().status).body(errorResponse);
  }


  @ExceptionHandler(MethodArgumentNotValidException.class)
  ResponseEntity<Map<String, String>> validateErrorHandler(MethodArgumentNotValidException e) {
    Map<String, String> resultMap = new HashMap<>();

    for (FieldError error : e.getBindingResult().getFieldErrors()) {
      resultMap.put(error.getField(), error.getDefaultMessage());
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultMap);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException e) {
    log.error(e.getMessage(), e);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버측 장애가 발생하였습니다.");
  }
}
