package br.com.fiap.clyvopaws.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI clyvoVetOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clyvo Vet API - Challenge FIAP 2026")
                        .description("API RESTful para o Sistema de Cuidado Contínuo e Plano de Alta Inteligente da Clyvo Vet.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipe [LevelUp]")));
    }
}