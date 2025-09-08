package co.edu.uniquindio.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) para solicitudes de cambio de contraseña.
 *
 * Contiene la información necesaria para que un usuario pueda cambiar su contraseña:
 * - email: Correo electrónico del usuario
 * - currentPassword: Contraseña actual del usuario
 * - newPassword: Nueva contraseña que desea establecer
 */
@Data // Genera automáticamente getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
public class ChangePasswordRequestDto {
    private String email; // Correo del usuario
    private String currentPassword; // Contraseña actual
    private String newPassword; // Nueva contraseña
}
