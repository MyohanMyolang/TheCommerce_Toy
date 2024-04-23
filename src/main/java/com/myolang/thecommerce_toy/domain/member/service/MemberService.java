package com.myolang.thecommerce_toy.domain.member.service;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public Member saveMember(Member member) {
    return memberRepository.saveMember(member);
  }


}
