package com.codeit.sprint.team3.backend.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String jwt = "JWT";
        Components components = new Components().addSecuritySchemes(jwt, new SecurityScheme()
                .name(jwt)
                .type(SecurityScheme.Type.HTTP)
                .scheme("Bearer")
                .bearerFormat("JWT")
        );
        Server server = new Server();
        server.setUrl("https://d3eoy4ym225l85.cloudfront.net");
        server.setDescription("CloudFront 배포 서버");

        return new OpenAPI()
                .servers(Collections.singletonList(server))
                .info(apiInfo())
                .components(components);
    }

    private Info apiInfo() {
        return new Info()
                .title("API") // API의 제목
                .description("코드잇 3팀 백엔드 API") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
