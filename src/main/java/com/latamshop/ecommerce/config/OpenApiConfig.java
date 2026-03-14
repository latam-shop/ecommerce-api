package com.latamshop.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenApi() {

    final String securitySchemeName = "bearerAuth";

    return new OpenAPI()
        .info(
            new Info()
                .title("Ecommerce API")
                .version("1.0")
                .description("API del sistema ecommerce")
                .contact(new Contact().name("Equipo Backend").email("backend@empresa.com")))
        .servers(
            List.of(
                new Server().url("http://localhost:9090").description("Local development server")))

        // JWT configuration
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .schemaRequirement(
            securitySchemeName,
            new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT"));
  }

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("Public API").pathsToMatch("/api/public/**").build();
  }

  @Bean
  public GroupedOpenApi adminApi() {
    return GroupedOpenApi.builder().group("Admin API").pathsToMatch("/api/admin/**").build();
  }
}
