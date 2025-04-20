package com.dartlinwave.platform.lawconnectplatform.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
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

        return openApi;
    }
}
