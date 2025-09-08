package co.edu.uniquindio.proyecto.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para la respuesta de autenticación.
 *
 * Contiene los datos que se devuelven al usuario después de un login exitoso:
 * - token: JWT (JSON Web Token) que se usará para autenticar futuras solicitudes
 * - role: Rol del usuario (ej. ROLE_ADMIN, ROLE_USER)
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@Builder // Permite construir instancias del DTO usando el patrón Builder
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor // Genera un constructor sin argumentos
public class TokenRespuesta {

    private String token; // JWT generado después del login
    private String role;  // Rol del usuario
}
