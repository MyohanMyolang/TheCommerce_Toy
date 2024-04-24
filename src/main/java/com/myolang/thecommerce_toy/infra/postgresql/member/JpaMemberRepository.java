package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

public interface JpaMemberRepository extends CrudRepository<MemberEntity, String> {
}
