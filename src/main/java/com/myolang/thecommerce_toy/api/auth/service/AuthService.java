package com.myolang.thecommerce_toy.api.auth.service;

import com.myolang.thecommerce_toy.api.auth.dto.RegisterMemberRequest;
import com.myolang.thecommerce_toy.domain.member.dto.MemberInfoResponse;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final MemberService memberService;
  private final PasswordEncoder passwordEncoder;

  public MemberInfoResponse registerMember(RegisterMemberRequest request) {
    memberService.isHasMemberId(request.getMemberId());
    memberService.isHasNickname(request.getNickname());

    Member member = request.toMember().toBuilder()
      .password(passwordEncoder.encode(request.getPassword()))
      .build();

    Member savedMember = memberService.saveMember(member);

    return MemberInfoResponse.from(savedMember);
  }
}
