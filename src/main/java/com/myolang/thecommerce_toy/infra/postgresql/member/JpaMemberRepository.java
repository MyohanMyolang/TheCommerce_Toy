package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface JpaMemberRepository extends CrudRepository<MemberEntity, String> {

  Optional<MemberEntity> findByNickname(String Nickname);
}
