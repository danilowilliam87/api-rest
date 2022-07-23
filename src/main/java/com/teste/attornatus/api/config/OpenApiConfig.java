package com.teste.attornatus.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIDocumentation(){
        return new OpenAPI()
                .info(
                  new Info()
                          .title("PESSOAS API-REST")
                          .description("Gerenciamento de Pessoas")
                          .version("v1.0")
                          .contact(new Contact()
                                  .name("Danilo William")
                                  .email("danilowilliam87@outloook.com"))

                );
    }
}
