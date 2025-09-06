package co.edu.uniquindio.proyecto.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8082", "http://localhost:8082") //Configura y da acceso a cors para estos llamados
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") //Permite estos metodos http
                .allowedHeaders("*") //Permite cualquier encabezado
                .allowCredentials(true);
    }
}