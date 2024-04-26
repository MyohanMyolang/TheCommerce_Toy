package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import com.myolang.thecommerce_toy.infra.postgresql.member.entity.QMemberEntity;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QueryDslMemberRepository {

  private final QMemberEntity member = QMemberEntity.memberEntity;
  private final JPAQueryFactory queryFactory;


  public List<MemberEntity> getMembersSortAndPage(Long page, Long pageSize, MemberSortType sortType) {
    return queryFactory
      .selectFrom(member)
      .limit(pageSize)
      .offset((page - 1) * pageSize)
      .orderBy(getSpecifier(sortType))
      .fetch();
  }

  public OrderSpecifier getSpecifier(MemberSortType sortType) {
    OrderSpecifier result = null;
    switch (sortType) {
      case NAME:
        result = new OrderSpecifier(Order.ASC, member.name);
        break;
      case CREATE_DATE:
        result = new OrderSpecifier(Order.ASC, member.createdAt);
        break;
    }
    return result;
  }
}