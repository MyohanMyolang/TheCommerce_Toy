package com.myolang.thecommerce_toy.api.auth.controller;

import com.myolang.thecommerce_toy.api.auth.dto.RegisterMemberRequest;
import com.myolang.thecommerce_toy.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/join")
  public ResponseEntity<String> registerMember(
    @RequestBody @Valid RegisterMemberRequest registerMemberRequest) {
    authService.registerMember(registerMemberRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공하였습니다.");
  }
}
