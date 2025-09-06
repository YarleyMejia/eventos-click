package co.edu.uniquindio.proyecto.configuracion;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI proyectoEventosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - Proyecto Eventos")
                        .description("Documentación de la API para gestión de eventos.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo UQ")
                                .email("soporte@uniquindio.edu.co")
                        )
                        .license(new License()
                                .name("Licencia Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub")
                        .url("https://github.com/YarleyMejia/eventos-click.git"));
    }
}
