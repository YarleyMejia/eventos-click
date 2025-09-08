package co.edu.uniquindio.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para solicitudes de inicio de sesión.
 *
 * Contiene la información necesaria para que un usuario pueda autenticarse:
 * - email: Correo electrónico del usuario
 * - password: Contraseña del usuario
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@Builder // Permite construir instancias del DTO usando el patrón Builder
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
public class LoginRequestDto {

    private String email; // Correo del usuario para autenticación
    private String password; // Contraseña del usuario
}

