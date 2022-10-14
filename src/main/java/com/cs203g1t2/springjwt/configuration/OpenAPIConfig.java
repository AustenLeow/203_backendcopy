package com.cs203g1t2.springjwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
@OpenAPIDefinition(
  info = @Info(
  title = "API doc",
  version = "1.0.0",
  description = "This is a simple API for managing books...",
    contact = @Contact(
    name = "CS203", 
    url = "Your Website", 
    email = "youremail@smu.edu.sg"
  ),
  license = @License(
    name = "Apache License 2.0", 
    url = "NA")),
  servers = @Server(url = "http://localhost:8080")
//   security = {"api_key": [cs203g1t2SecretKey]}
)
public class OpenAPIConfig {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
        .addSecurityItem(new SecurityRequirement()
            .addList(securitySchemeName))
        .components(new Components()
            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
            .name(securitySchemeName)
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")));
    }
}
