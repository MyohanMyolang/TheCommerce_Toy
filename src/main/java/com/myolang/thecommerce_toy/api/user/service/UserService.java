package com.myolang.thecommerce_toy.api.user.service;

import com.myolang.thecommerce_toy.api.user.dto.UpdateUserInfoRequest;
import com.myolang.thecommerce_toy.domain.member.dto.MembersPagingResponse;
import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.IMemberRepository;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

  private final MemberService memberService;
  private final IMemberRepository memberRepository;

  @Transactional
  public void updateUserInfo(String memberId, UpdateUserInfoRequest request) {
    Member member = memberService.getMemberById(memberId);

    request.getNickname().ifPresent(memberService::isHasNickname);

    Member updatedMember = member.toBuilder()
      .nickname(request.getNickname().orElse(member.getNickname()))
      .name(request.getName().orElse(member.getName()))
      .phoneNumber(request.getPhoneNumber().orElse(member.getPhoneNumber()))
      .email(request.getEmail().orElse(member.getEmail()))
      .password(request.getPassword().orElse(member.getPassword()))
      .build();

    memberService.saveMember(updatedMember);
  }

  public MembersPagingResponse getMembers(Long page, Long pageSize, MemberSortType sortType) {
    List<Member> members = memberService.getMembers(page, pageSize, sortType);
    Long totalMemberCount = memberRepository.getTotalMemberCount();

    return MembersPagingResponse.of(members, totalMemberCount, pageSize);
  }
}
