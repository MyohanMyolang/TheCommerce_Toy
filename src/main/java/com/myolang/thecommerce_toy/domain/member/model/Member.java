package com.myolang.thecommerce_toy.domain.member.model;

import lombok.Builder;
import lombok.Getter;


@Getter
public class Member {
  private final Long id;
  private final String memberId;
  private final String password;
  private final String nickname;
  private final String name;
  private final String phoneNumber;
  private final String email;

  @Builder(toBuilder = true)
  public Member(Long id, String memberId, String password, String nickname, String name, String phoneNumber, String email) {
    this.id = id;
    this.memberId = memberId;
    this.password = password;
    this.nickname = nickname;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}
