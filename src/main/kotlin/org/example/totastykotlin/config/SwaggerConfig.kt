package org.example.totastykotlin.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun toTastyApi(): OpenAPI {
        return OpenAPI().info(Info().title("To-Tasty API").description("ToTasty API 명세서").version("v1"))
            .components(Components())
            .addSecurityItem(SecurityRequirement())
    }

}