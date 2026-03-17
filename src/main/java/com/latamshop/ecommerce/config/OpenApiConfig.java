package com.latamshop.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
  @Bean
  public OpenAPI customOpenApi(
      @Value("${server.apiUrl}") String apiUrl,
      @Value("${server.email}") String emailTeam,
      @Value("${server.port}") String apiPort) {
    final String securitySchemeName = "bearerAuth";

    return new OpenAPI()
        .info(this.apiInfo(emailTeam))
        .servers(List.of(new Server().url(apiUrl).description("API principal")))
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

  @Bean
  public GroupedOpenApi actuatorApi(
      @Value("${server.apiUrl}") String apiUrl,
      @Value("${management.server.port:9091}") String actuatorPort) {
    final String actuatorUrl = apiUrl.replaceFirst(":\\d+", ":" + actuatorPort);
    return GroupedOpenApi.builder()
        .group("x-actuator")
        .displayName("Actuator")
        .pathsToMatch("/actuator/**")
        .addOpenApiCustomizer(
            openApi -> {
              openApi.getServers().clear();
              openApi.addServersItem(
                  new Server()
                      .url(actuatorUrl) // O la URL que corresponda
                      .description("Servidor de Metricas"));
            })
        .build();
  }

  private Info apiInfo(String emailTeam) {
    return new Info()
        .title("Backend Ecommerce API")
        .version("1.0")
        .description("Rest Ecommerce API")
        .termsOfService("Términos de servicio")
        .contact(new Contact().name("Equipo Backend").email(emailTeam))
        .license(
            new License()
                .name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0.html"));
  }
}
