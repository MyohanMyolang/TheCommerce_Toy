package com.myolang.thecommerce_toy.domain.member.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class Member {
  private final String memberId;
  private final String password;
  private final String nickname;
  private final String name;
  private final String phoneNumber;
  private final String email;
  private final LocalDateTime createdAt;

  @Builder(toBuilder = true)
  public Member(String memberId, String password, String nickname, String name, String phoneNumber, String email, LocalDateTime createdAt) {
    this.memberId = memberId;
    this.password = password;
    this.nickname = nickname;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.createdAt = createdAt;
  }
}
