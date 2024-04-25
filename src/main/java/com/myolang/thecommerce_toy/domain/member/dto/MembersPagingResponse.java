package com.myolang.thecommerce_toy.domain.member.dto;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class MembersPagingResponse {
  private final List<MemberInfoResponse> members;
  private final Long totalMemberCount;
  private final Long totalPage;


  public static MembersPagingResponse of(List<Member> members, Long totalMemberCount, Long pageSize) {
    Long totalPage = totalMemberCount % pageSize > 0 ?
      totalMemberCount / pageSize + 1 : totalMemberCount / pageSize;

    List<MemberInfoResponse> memberInfos = members.stream()
      .map(MemberInfoResponse::from)
      .collect(Collectors.toList());

    return new MembersPagingResponse(memberInfos, totalMemberCount, totalPage);
  }

}
