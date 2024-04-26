package com.myolang.thecommerce_toy.domain.member.repository;

import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.domain.member.model.Member;

import java.util.List;

public interface IMemberRepository {
  Member saveMember(Member member);

  Member getMemberById(String memberId);

  List<Member> getMembers(Long page, Long pageSize, MemberSortType sortType);

  Long getTotalMemberCount();

  public Boolean isHasMemberId(String memberId);

  public Boolean isHasNickname(String nickname);
}
