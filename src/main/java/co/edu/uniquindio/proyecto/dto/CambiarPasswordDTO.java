package co.edu.uniquindio.proyecto.dto;

/**
 * DTO (Data Transfer Object) para solicitudes de cambio de contraseña mediante verificación.
 *
 * Este DTO se utiliza cuando un usuario quiere cambiar su contraseña usando un código de verificación enviado a su correo.
 *
 * @param email Correo electrónico del usuario
 * @param codigoVerificacion Código de verificación recibido por el usuario
 * @param passwordNueva Nueva contraseña que desea establecer
 */
public record CambiarPasswordDTO(
        String email,               // Correo del usuario
        String codigoVerificacion,  // Código recibido para validar el cambio de contraseña
        String passwordNueva        // Nueva contraseña que se desea establecer
) {
}
