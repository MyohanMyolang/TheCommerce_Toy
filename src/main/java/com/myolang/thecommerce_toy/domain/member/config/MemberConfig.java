package com.myolang.thecommerce_toy.domain.member.config;

import com.myolang.thecommerce_toy.domain.member.repository.MemberRepository;
import com.myolang.thecommerce_toy.domain.member.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfig {

  @Bean
  public MemberService memberServiceConfig(MemberRepository memberRepository) {
    return new MemberService(memberRepository);
  }
}
