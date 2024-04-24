package com.myolang.thecommerce_toy.api.auth.dto;

import com.myolang.thecommerce_toy.domain.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class RegisterMemberRequest {
  @NotBlank(message = "ID를 입력하여 주십시오.")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "소문자, 대문자, 숫자로만 이루어져야 합니다.")
  @Size(min = 4, max = 16, message = "ID는 4-16 글자로 작성되어야 합니다.")
  private final String memberId;

  @NotBlank(message = "PASSWORD를 입력하여 주십시오.")
  @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).*$", message = "대문자, 숫자, 특수 문자를 포함 시켜 주십시오.")
  @Size(min = 8, max = 16, message = "PASSWORD는 8글자 이상으로 작성되어야 합니다.")
  private final String password;

  @NotBlank(message = "Nickname을 입력하여 주십시오.")
  @Pattern(regexp = "^[A-Za-z0-9ㄱ-힣]*$", message = "닉네임 형식이 맞지 않습니다.")
  private final String nickname;

  @NotBlank(message = "이름을 입력하여 주십시오.")
  @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능 합니다.")
  private final String name;

  @NotBlank(message = "휴대폰 번호를 입력하여 주십시오.")
  @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "010-xxxx-xxxx의 형태로 입력하여 주십시오.")
  private final String phoneNumber;

  @NotBlank(message = "Email을 입력하여 주십시오.")
  @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "EMail 규칙에 맞지 않습니다.")
  private final String email;

  public Member toMember() {
    return Member.builder()
      .memberId(memberId)
      .name(name)
      .email(email)
      .phoneNumber(phoneNumber)
      .password(password)
      .nickname(nickname)
      .build();
  }
}
