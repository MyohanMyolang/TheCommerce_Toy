package com.myolang.thecommerce_toy.api.auth.service;

import com.myolang.thecommerce_toy.api.auth.dto.RegisterMemberRequest;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final MemberService memberService;

  public void registerMember(RegisterMemberRequest request) {
    memberService.saveMember(request.toMember());
  }
}
