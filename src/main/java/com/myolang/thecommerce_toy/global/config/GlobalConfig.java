package com.myolang.thecommerce_toy.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class GlobalConfig {

  @Bean
  public PasswordEncoder passwordEncoderConfig() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
