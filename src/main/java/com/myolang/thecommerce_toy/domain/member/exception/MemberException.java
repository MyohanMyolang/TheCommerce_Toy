package com.myolang.thecommerce_toy.domain.member.exception;

import com.myolang.thecommerce_toy.global.exception.CustomException;
import com.myolang.thecommerce_toy.global.exception.ErrorCode;

public class MemberException extends CustomException {
  public String message;
  public ErrorCode errorCode;
  public Object payload;

  public MemberException(ErrorCode errorCode) {
    super(errorCode.getMessage(), errorCode, null);
  }

  public MemberException(String message, ErrorCode errorCode) {
    super(message, errorCode, null);
  }

  public MemberException(String message, ErrorCode errorCode, Object payload) {
    super(message, errorCode, payload);
  }

  public MemberException(ErrorCode errorCode, Object payload) {
    super(errorCode.getMessage(), errorCode, payload);
  }
}
