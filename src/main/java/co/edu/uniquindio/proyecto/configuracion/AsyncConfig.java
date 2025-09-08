package co.edu.uniquindio.proyecto.configuracion;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Configuración para habilitar la ejecución de métodos de manera asíncrona
 * en la aplicación Spring.
 *
 * Esta clase implementa AsyncConfigurer para permitir personalizar el
 * comportamiento de ejecución asíncrona si se desea en el futuro.
 */
@Configuration // Marca la clase como configuración de Spring
@EnableAsync   // Habilita la ejecución asíncrona de métodos anotados con @Async
public class AsyncConfig implements AsyncConfigurer {
    // Actualmente no se personaliza el Executor,
    // pero se puede sobrescribir los métodos de AsyncConfigurer si se desea.
}
