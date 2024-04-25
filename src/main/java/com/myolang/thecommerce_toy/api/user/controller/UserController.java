package com.myolang.thecommerce_toy.api.user.controller;

import com.myolang.thecommerce_toy.api.user.dto.UpdateUserInfoRequest;
import com.myolang.thecommerce_toy.api.user.service.UserService;
import com.myolang.thecommerce_toy.domain.member.dto.MembersPagingResponse;
import com.myolang.thecommerce_toy.domain.member.enums.MemberSortType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @GetMapping("/list")
  public ResponseEntity<MembersPagingResponse> getMembers(
    @RequestParam Long page,
    @RequestParam Long pageSize,
    @RequestParam MemberSortType sortType
  ) {
    MembersPagingResponse response = userService.getMembers(page, pageSize, sortType);
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @PatchMapping("/{loginId}")
  public ResponseEntity<String> updateMemberInfo(
    @PathVariable String loginId,
    @RequestBody @Valid UpdateUserInfoRequest updateUserInfoRequest
  ) {
    userService.updateUserInfo(loginId, updateUserInfoRequest);
    return ResponseEntity.status(HttpStatus.OK).body("업데이트가 성공하였습니다.");
  }
}
