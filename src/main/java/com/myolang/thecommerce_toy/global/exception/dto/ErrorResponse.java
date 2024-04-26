package com.myolang.thecommerce_toy.global.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  String message;
  Long errorCode;
  Object payload;

  public static ErrorResponse of(String message, Long errorCode, Object payload) {
    return new ErrorResponse(message, errorCode, payload);
  }
}
