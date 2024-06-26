package com.myolang.thecommerce_toy.infra.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPIConfig() {
    return new OpenAPI()
      .components(new Components())
      .info(new Info()
        .title("TheCommerce_Toy")
        .description("API 명세")
        .version("1.0.0")
      );
  }
}
