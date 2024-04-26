package com.myolang.thecommerce_toy.domain.member.dto;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberInfoResponse {
  private final String memberId;
  private final String nickname;
  private final String name;
  private final String phoneNumber;
  private final String email;
  private final LocalDateTime createdAt;

  public static MemberInfoResponse from(Member member) {
    return new MemberInfoResponse(
      member.getMemberId(),
      member.getNickname(),
      member.getName(),
      member.getPhoneNumber(),
      member.getEmail(),
      member.getCreatedAt()
    );
  }
}
