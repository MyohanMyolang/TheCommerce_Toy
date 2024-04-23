package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.MemberRepository;
import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import com.myolang.thecommerce_toy.infra.postgresql.member.util.MemberEntityConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class PostgresqlMemberRepository extends MemberEntityConvertUtil implements MemberRepository {

  private final JpaMemberRepository jpaMemberRepository;


  @Override
  @Transactional
  public Member saveMember(Member member) {
    MemberEntity memberEntity = toMemberEntity(member);
    jpaMemberRepository.save(memberEntity);
    return toMember(memberEntity);
  }

  public List<Member> getMembers() {
    return jpaMemberRepository.findAll()
      .stream()
      .map((this::toMember))
      .collect(Collectors.toList());
  }
}
