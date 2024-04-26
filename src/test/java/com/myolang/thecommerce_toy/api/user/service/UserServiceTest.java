package com.myolang.thecommerce_toy.api.user.service;

import com.myolang.thecommerce_toy.api.user.dto.UpdateUserInfoRequest;
import com.myolang.thecommerce_toy.domain.member.dto.MemberInfoResponse;
import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import com.myolang.thecommerce_toy.domain.member.exception.MemberException;
import com.myolang.thecommerce_toy.domain.member.model.Member;
import com.myolang.thecommerce_toy.domain.member.repository.IMemberRepository;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

  @Nested
  @DisplayName("회원 정보 수정 테스트")
  class updateUserInfoTest {
    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return "Encoded Password";
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
      }
    };
    private boolean isDuplicatedNickname;
    private final Member registedMember = Member.builder()
      .memberId("test")
      .password("test")
      .email("test")
      .name("test")
      .phoneNumber("test")
      .nickname("test")
      .createdAt(LocalDateTime.now())
      .build();
    private final IMemberRepository memberRepository = new IMemberRepository() {
      @Override
      public Member saveMember(Member member) {
        return member;
      }

      @Override
      public Member getMemberById(String memberId) {
        return registedMember;
      }

      @Override
      public List<Member> getMembers(Long page, Long pageSize, MemberSortType sortType) {
        return null;
      }

      @Override
      public Long getTotalMemberCount() {
        return null;
      }

      @Override
      public Boolean isHasMemberId(String memberId) {
        return null;
      }

      @Override
      public Boolean isHasNickname(String nickname) {
        return isDuplicatedNickname;
      }
    };

    private final MemberService memberService = new MemberService(memberRepository);

    private final UserService userService = new UserService(memberService, memberRepository, passwordEncoder);


    @Nested
    @DisplayName("중복된 Nickname일 때")
    class DuplicatedNickname {

      private DuplicatedNickname() {
        isDuplicatedNickname = true;
      }

      @Test
      @DisplayName("MemberException이 발생하고 ErrorCode는 20002이다.")
      void throwAuthExceptionAndErrorCode20002() {
        UpdateUserInfoRequest request = new UpdateUserInfoRequest(
          Optional.of("test"),
          Optional.empty(),
          Optional.empty(),
          Optional.empty(),
          Optional.empty()
        );
        MemberException memberException = assertThrows(MemberException.class, () -> userService.updateUserInfo("test", request));


        assertThat(memberException.getErrorCode().getCode()).isEqualTo(20002L);
      }
    }

    @Nested
    @DisplayName("일부 값만 수정 요청이 들어왔을 때")
    class SuccessUpdateUserInfo {
      private SuccessUpdateUserInfo() {
        isDuplicatedNickname = false;
      }

      @Test
      @DisplayName("정상적으로 저장 된다.")
      void EncryptedPassword() {
        UpdateUserInfoRequest request = new UpdateUserInfoRequest(
          Optional.of("Changed nickname"),
          Optional.empty(),
          Optional.of("Changed name"),
          Optional.empty(),
          Optional.empty()
        );
        MemberInfoResponse memberInfoResponse = userService.updateUserInfo("test", request);

        assertThat(memberInfoResponse.getNickname()).isEqualTo("Changed nickname");
        assertThat(memberInfoResponse.getName()).isEqualTo("Changed name");
        assertThat(memberInfoResponse.getPhoneNumber()).isEqualTo("test");
        assertThat(memberInfoResponse.getEmail()).isEqualTo("test");
      }
    }
  }
}