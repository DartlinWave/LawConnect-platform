
package com.dartlinwave.platform.lawconnectplatform.shared.infrastructure.documentation.openapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * <p>
 * Sets up the OpenAPI bean with application metadata, external documentation,
 * and JWT bearer authentication security scheme.
 * </p>
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * The name of the application, injected from the application properties.
     */
    @Value("${spring.application.name}")
    String applicationName;

    /**
     * The version of the application, injected from the application properties.
     */
    @Value("${documentation.application.version}")
    String applicationVersion;

    /**
     * The description of the application, injected from the application properties.
     */
    @Value("${documentation.application.description}")
    String applicationDescription;

    /**
     * Creates and configures the OpenAPI bean for Swagger UI.
     *
     * @return the configured {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI lawConnectOpenApi() {
        var openApi = new OpenAPI();

        openApi.info(new Info()
                        .title("Law Connect Application API")
                        .description("Law Connect Application API documentation.")
                        .version("v1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Law Connect Github Repository")
                        .url("https://github.com/DartlinWave/LawConnect-platform"));

        String securitySchemeName = "bearerAuth";
        openApi.addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));

        return openApi;
    }
}