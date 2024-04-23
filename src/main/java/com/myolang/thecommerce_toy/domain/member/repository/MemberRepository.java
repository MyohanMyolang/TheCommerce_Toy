package com.myolang.thecommerce_toy.domain.member.repository;

import com.myolang.thecommerce_toy.domain.member.model.Member;

public interface MemberRepository {
  Member saveMember(Member member);
}
