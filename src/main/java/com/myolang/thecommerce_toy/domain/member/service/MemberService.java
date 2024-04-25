package com.myolang.thecommerce_toy.domain.member.service;

import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.domain.member.exception.MemberException;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.IMemberRepository;
import com.myolang.thecommerce_toy.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberService {
  private final IMemberRepository memberRepository;

  public Member getMemberById(String memberId) {
    return memberRepository.getMemberById(memberId);
  }

  public Member saveMember(Member member) {
    return memberRepository.saveMember(member);
  }

  public List<Member> getMembers(Long page, Long pageSize, MemberSortType sortType) {
    return memberRepository.getMembers(page, pageSize, sortType);
  }

}
