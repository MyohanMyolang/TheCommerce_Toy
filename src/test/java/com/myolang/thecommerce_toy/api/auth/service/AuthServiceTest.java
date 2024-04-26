package com.myolang.thecommerce_toy.api.auth.service;

import com.myolang.thecommerce_toy.api.auth.dto.RegisterMemberRequest;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AuthServiceTest {

  @Nested
  @DisplayName("회원가입 테스트")
  class RegisterTest {
    private final PasswordEncoder passwordEncoder = new PasswordEncoder() {
      @Override
      public String encode(CharSequence rawPassword) {
        return "encoded Password";
      }

      @Override
      public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return false;
      }
    };

    private boolean isDuplicatedMemberId;
    private boolean isDuplicatedNickname;
    private final IMemberRepository memberRepository = new IMemberRepository() {
      @Override
      public Member saveMember(Member member) {
        return member;
      }

      @Override
      public Member getMemberById(String memberId) {
        return null;
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
        return isDuplicatedMemberId;
      }

      @Override
      public Boolean isHasNickname(String nickname) {
        return isDuplicatedNickname;
      }
    };
    private final AuthService authService = new AuthService(new MemberService(memberRepository), passwordEncoder);


    @Nested
    @DisplayName("아이디가 중복되었다면")
    class DuplicatedMemberId {

      private DuplicatedMemberId() {
        isDuplicatedMemberId = true;
        isDuplicatedNickname = false;
      }

      @Test
      @DisplayName("MemberException이 발생하고 ErrorCode는 20001이다.")
      void throwAuthExceptionAndErrorCode20001() {
        RegisterMemberRequest request = new RegisterMemberRequest
          ("test", "test", "test", "test", "test", "test");

        MemberException memberException = assertThrows(MemberException.class, () -> authService.registerMember(request));


        assertThat(memberException.getErrorCode().getCode()).isEqualTo(20001L);
      }
    }

    @Nested
    @DisplayName("닉네임이 중복되었다면")
    class DuplicatedNickname {

      private DuplicatedNickname() {
        isDuplicatedMemberId = false;
        isDuplicatedNickname = true;
      }

      @Test
      @DisplayName("MemberException이 발생하고 ErrorCode는 20002이다.")
      void throwAuthExceptionAndErrorCode20002() {
        RegisterMemberRequest request = new RegisterMemberRequest
          ("test", "test", "test", "test", "test", "test");

        MemberException memberException = assertThrows(MemberException.class, () -> authService.registerMember(request));


        assertThat(memberException.getErrorCode().getCode()).isEqualTo(20002L);
      }
    }

    @Nested
    @DisplayName("회원가입이 성공 한다면")
    class SuccessRegister {
      private SuccessRegister() {
        isDuplicatedMemberId = false;
        isDuplicatedNickname = false;
      }

      @Test
      @DisplayName("요청 정보와 일치하는 데이터를 반환한다.")
      void returnSameData() {
        RegisterMemberRequest request = new RegisterMemberRequest
          ("test", "test", "test", "test", "test", "test");
        MemberInfoResponse response = authService.registerMember(request);

        assertThat(response.getMemberId()).isEqualTo("test");
        assertThat(response.getNickname()).isEqualTo("test");
        assertThat(response.getPhoneNumber()).isEqualTo("test");
        assertThat(response.getName()).isEqualTo("test");
        assertThat(response.getEmail()).isEqualTo("test");
      }
    }
  }
}