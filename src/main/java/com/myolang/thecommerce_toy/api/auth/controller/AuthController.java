package com.myolang.thecommerce_toy.api.auth.controller;

import com.myolang.thecommerce_toy.api.auth.dto.RegisterMemberRequest;
import com.myolang.thecommerce_toy.api.auth.service.AuthService;
import com.myolang.thecommerce_toy.domain.member.dto.MemberInfoResponse;
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
  public ResponseEntity<MemberInfoResponse> registerMember(
    @RequestBody @Valid RegisterMemberRequest registerMemberRequest) {
    MemberInfoResponse memberInfoResponse = authService.registerMember(registerMemberRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(memberInfoResponse);
  }
}
