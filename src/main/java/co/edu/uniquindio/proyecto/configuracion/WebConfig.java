package co.edu.uniquindio.proyecto.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de la aplicación para habilitar CORS (Cross-Origin Resource Sharing).
 *
 * Permite que aplicaciones frontend (como Angular en localhost:4200)
 * puedan consumir la API sin problemas de origen cruzado.
 */
@Configuration // Marca la clase como configuración de Spring
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura los mapeos de CORS.
     *
     * @param registry Registro de configuraciones CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos los endpoints de la API
                .allowedOrigins("http://localhost:4200") // Permite solicitudes desde Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true); // Permite enviar cookies y credenciales
    }
}
