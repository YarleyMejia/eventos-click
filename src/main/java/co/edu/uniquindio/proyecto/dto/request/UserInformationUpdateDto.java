package co.edu.uniquindio.proyecto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para solicitudes de actualización de información de usuario.
 *
 * Contiene los datos que un usuario puede actualizar en su perfil:
 * - email: Correo electrónico del usuario
 * - name: Nombre del usuario
 * - lastname: Apellidos del usuario
 * - address: Dirección del usuario
 * - phone: Teléfono de contacto del usuario
 */
@Getter // Genera automáticamente getters para todos los atributos
@Setter // Genera automáticamente setters para todos los atributos
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor // Genera un constructor sin argumentos
public class UserInformationUpdateDto {

    private String email;    // Correo electrónico del usuario
    private String name;     // Nombre del usuario
    private String lastname; // Apellidos del usuario
    private String address;  // Dirección del usuario
    private String phone;    // Teléfono de contacto del usuario
}
