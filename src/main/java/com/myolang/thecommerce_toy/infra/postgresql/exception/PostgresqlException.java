package com.myolang.thecommerce_toy.infra.postgresql.exception;

import com.myolang.thecommerce_toy.global.exception.CustomException;
import com.myolang.thecommerce_toy.global.exception.ErrorCode;

public class PostgresqlException extends CustomException {
  public String message;
  public ErrorCode errorCode;
  public Object payload;

  public PostgresqlException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode, null);
  }

  public PostgresqlException(String message, ErrorCode errorCode) {
    super(message, errorCode, null);
  }

  public PostgresqlException(String message, ErrorCode errorCode, Object payload) {
    super(message, errorCode, payload);
  }

  public PostgresqlException(ErrorCode errorCode, Object payload) {
    super(errorCode.getMessage(), errorCode, payload);
  }
}
