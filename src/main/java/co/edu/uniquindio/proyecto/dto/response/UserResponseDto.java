package co.edu.uniquindio.proyecto.dto.response;

import lombok.*;

/**
 * DTO (Data Transfer Object) para la respuesta de información de un usuario.
 *
 * Contiene los datos que se envían al cliente después de consultar o actualizar
 * la información de un usuario:
 * - name: Nombre del usuario
 * - lastname: Apellidos del usuario
 * - address: Dirección del usuario
 * - phone: Teléfono de contacto del usuario
 */
@Getter // Genera automáticamente getters para todos los atributos
@Setter // Genera automáticamente setters para todos los atributos
@AllArgsConstructor // Genera un constructor con todos los atributos
@NoArgsConstructor // Genera un constructor sin argumentos
@Builder // Permite construir instancias del DTO usando el patrón Builder
public class UserResponseDto {

    private String name;     // Nombre del usuario
    private String lastname; // Apellidos del usuario
    private String address;  // Dirección del usuario
    private String phone;    // Teléfono de contacto del usuario
}
