package com.myolang.thecommerce_toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TheCommerceToyApplication {

  public static void main(String[] args) {
    SpringApplication.run(TheCommerceToyApplication.class, args);
  }

}
