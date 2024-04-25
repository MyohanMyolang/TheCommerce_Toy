package com.myolang.thecommerce_toy.infra.postgresql.member;

import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.IMemberRepository;
import com.myolang.thecommerce_toy.global.exception.ErrorCode;
import com.myolang.thecommerce_toy.infra.postgresql.exception.PostgresqlException;
import com.myolang.thecommerce_toy.infra.postgresql.member.entity.MemberEntity;
import com.myolang.thecommerce_toy.infra.postgresql.member.util.MemberEntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PostgresqlMemberRepository extends MemberEntityUtil implements IMemberRepository {

  private final JpaMemberRepository jpaMemberRepository;
  private final QueryDslMemberRepository queryDslMemberRepository;

  @Override
  @Transactional
  public Member saveMember(Member member) {
    MemberEntity memberEntity = modelToEntity(member);
    jpaMemberRepository.save(memberEntity);
    return entityToModel(memberEntity);
  }

  @Override
  public Member getMemberById(String memberId) {
    MemberEntity memberEntity = jpaMemberRepository.findById(memberId)
      .orElseThrow(() -> new PostgresqlException(ErrorCode.NOT_FOUND_MEMBER));

    return entityToModel(memberEntity);
  }

  @Override
  public List<Member> getMembers(Long page, Long pageSize, MemberSortType sortType) {
    List<MemberEntity> members = queryDslMemberRepository.getMembersSortAndPage(page, pageSize, sortType);

    return entityListToModelList(members);
  }

  @Override
  public Long getTotalMemberCount() {
    return jpaMemberRepository.count();
  }

  @Override
  public Boolean isHasMemberId(String memberId) {
    return jpaMemberRepository.findById(memberId).isPresent();
  }

  @Override
  public Boolean isHasNickname(String nickname) {
    return jpaMemberRepository.findById(nickname).isPresent();
  }
}
