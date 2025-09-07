package co.edu.uniquindio.proyecto.configuracion;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI proyectoEventosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Proyecto Eventos Click")
                        .description("Documentación de la API para gestión de eventos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo UQ")
                                .email("josey.beltranm@uqvirtual.edu.co")
                        )
                        .license(new License()
                                .name("Licencia Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub")
                        .url("https://github.com/YarleyMejia/eventos-click.git"))
                // 🔐 Seguridad con JWT
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication",
                                new SecurityScheme()
                                        .name("Authorization")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
