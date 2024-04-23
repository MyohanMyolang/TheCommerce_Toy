package com.myolang.thecommerce_toy.api.auth.dto;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterMemberRequest {
  private final String memberId;
  private final String password;
  private final String nickname;
  private final String name;
  private final String phoneNumber;
  private final String email;

  public Member toMember() {
    return Member.builder()
      .id(null)
      .memberId(memberId)
      .name(name)
      .email(email)
      .phoneNumber(phoneNumber)
      .password(password)
      .nickname(nickname)
      .build();
  }
}
