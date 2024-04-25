package com.myolang.thecommerce_toy.api.user.service;

import com.myolang.thecommerce_toy.api.user.dto.UpdateUserInfoRequest;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final MemberService memberService;

  @Transactional
  public void updateUserInfo(String memberId, UpdateUserInfoRequest request) {
    Member member = memberService.getMemberById(memberId);

    Member updatedMember = member.toBuilder()
      .nickname(request.getNickname().orElse(member.getNickname()))
      .name(request.getNickname().orElse(member.getName()))
      .phoneNumber(request.getNickname().orElse(member.getPhoneNumber()))
      .email(request.getNickname().orElse(member.getEmail()))
      .password(request.getPassword().orElse(member.getPassword()))
      .build();

    memberService.saveMember(updatedMember);
  }
}
