package com.myolang.thecommerce_toy.infra.postgresql.member.entity;

import com.myolang.thecommerce_toy.infra.postgresql.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "member")
public class MemberEntity extends BaseEntity {

  @Setter
  @Id
  @Column(nullable = false, unique = true)
  private String memberId;

  @Setter
  @Column(nullable = false)
  private String password;

  @Setter
  @Column(nullable = false, unique = true)
  private String nickname;

  @Setter
  @Column(nullable = false)
  private String name;

  @Setter
  @Column(nullable = false)
  private String phoneNumber;

  @Setter
  @Column(nullable = false)
  private String email;

  @Builder
  public MemberEntity(String memberId, String password, String nickname, String name, String phoneNumber, String email) {
    this.memberId = memberId;
    this.password = password;
    this.nickname = nickname;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }
}
