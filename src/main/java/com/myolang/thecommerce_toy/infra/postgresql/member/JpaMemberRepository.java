package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
