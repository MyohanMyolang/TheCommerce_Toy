package com.myolang.thecommerce_toy.infra.postgresql.member.util;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import com.myolang.thecommerce_toy.infra.querydsl.EntityUtil;

public class MemberEntityUtil extends EntityUtil<MemberEntity, Member> {
  protected MemberEntity modelToEntity(Member member) {
    return MemberEntity.builder()
      .memberId(member.getMemberId())
      .name(member.getName())
      .email(member.getEmail())
      .phoneNumber(member.getPhoneNumber())
      .password(member.getPassword())
      .nickname(member.getNickname())
      .build();
  }

  protected Member entityToModel(MemberEntity memberEntity) {
    return Member.builder()
      .memberId(memberEntity.getMemberId())
      .name(memberEntity.getName())
      .email(memberEntity.getEmail())
      .phoneNumber(memberEntity.getPhoneNumber())
      .password(memberEntity.getPassword())
      .nickname(memberEntity.getNickname())
      .createdAt(memberEntity.getCreatedAt())
      .build();
  }
}
