package com.myolang.thecommerce_toy.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Getter
@AllArgsConstructor
public class UpdateUserInfoRequest {
  private final Optional<
    @Pattern(regexp = "^[A-Za-z0-9ㄱ-힣]*$", message = "닉네임 형식이 맞지 않습니다.")
      String> nickname;

  private final Optional<
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#?!@$%^&*-]).*$", message = "대문자, 숫자, 특수 문자를 포함 시켜 주십시오.")
    @Size(min = 8, max = 16, message = "PASSWORD는 8글자 이상으로 작성되어야 합니다.")
      String> password;


  private final Optional<
    @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능 합니다.")
      String> name;


  private final Optional<
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$", message = "010-xxxx-xxxx의 형태로 입력하여 주십시오.")
      String> phoneNumber;


  private final Optional<
    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "EMail 규칙에 맞지 않습니다.")
      String> email;
}
