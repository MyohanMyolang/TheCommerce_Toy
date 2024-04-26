package com.myolang.thecommerce_toy.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

  // POSTGRESQL 10001~20000
  NOT_FOUND_MEMBER(10001L, HttpStatus.NOT_FOUND, "해당 멤버를 찾지 못하였습니다."),

  // Member 20001~30000
  ALREADY_HAS_MEMBER_ID(20001L, HttpStatus.BAD_REQUEST, "이미 존재하는 ID입니다."),
  ALREADY_HAS_NICKNAME(20002L, HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다.");

  final Long code;
  final HttpStatus status;
  final String message;
}
