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

/**
 * Configuración de Swagger/OpenAPI para documentar la API REST.
 *
 * Define información general del proyecto, contacto, licencia,
 * documentación externa y esquema de seguridad JWT.
 */
@Configuration // Marca la clase como configuración de Spring
public class SwaggerConfig {

    /**
     * Configura y expone el bean OpenAPI para generar la documentación de Swagger.
     *
     * @return Objeto OpenAPI configurado con información del proyecto y seguridad
     */
    @Bean
    public OpenAPI proyectoEventosOpenAPI() {
        return new OpenAPI()
                // Información general de la API
                .info(new Info()
                        .title("API - Proyecto Eventos Click") // Título del proyecto
                        .description("Documentación de la API para gestión de eventos.") // Descripción
                        .version("1.0.0") // Versión de la API
                        .contact(new Contact() // Información de contacto
                                .name("Equipo de Desarrollo UQ")
                                .email("josey.beltranm@uqvirtual.edu.co")
                        )
                        .license(new License() // Licencia del proyecto
                                .name("Licencia Apache 2.0")
                                .url("http://springdoc.org")))
                // Documentación externa (GitHub)
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub")
                        .url("https://github.com/YarleyMejia/eventos-click.git"))
                // 🔐 Configuración de seguridad con JWT (Bearer)
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authentication", // Nombre del esquema
                                new SecurityScheme()
                                        .name("Authorization") // Nombre del encabezado HTTP
                                        .type(SecurityScheme.Type.HTTP) // Tipo HTTP
                                        .scheme("bearer") // Esquema Bearer
                                        .bearerFormat("JWT") // Formato JWT
                        )
                );
    }
}
