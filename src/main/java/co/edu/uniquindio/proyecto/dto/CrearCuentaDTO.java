package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * DTO (Data Transfer Object) para solicitudes de creación de cuenta de usuario.
 *
 * Contiene los datos necesarios para registrar un nuevo usuario en el sistema.
 *
 * Las anotaciones de validación (@NotBlank, @Length, @Email) reemplazan condicionales manuales
 * que normalmente se usarían para verificar los datos:
 * - @NotBlank asegura que el campo no esté vacío ni sea solo espacios
 * - @Length define la longitud mínima y/o máxima permitida
 * - @Email valida que el formato del correo sea correcto
 */
public record CrearCuentaDTO(

        @NotBlank @Length(max = 10) String cedula,         // Cédula del usuario, obligatorio, máximo 10 caracteres
        @NotBlank @Length(max = 100) String nombre,       // Nombre del usuario, obligatorio, máximo 100 caracteres
        @NotBlank @Length(max = 10) String telefono,      // Teléfono del usuario, obligatorio, máximo 10 caracteres
        @Length(max = 100) String direccion,             // Dirección del usuario, opcional, máximo 100 caracteres
        @NotBlank @Length(max = 40) @Email String email, // Correo electrónico, obligatorio, máximo 40 caracteres, formato válido
        @NotBlank @Length(min = 7, max = 20) String password // Contraseña, obligatorio, entre 7 y 20 caracteres
        // @Past LocalDate fechaNacimiento               // Opcional: fecha de nacimiento, debe ser pasada si se activa
) {
}
