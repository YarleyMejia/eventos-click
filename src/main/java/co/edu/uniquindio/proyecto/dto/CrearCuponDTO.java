package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.TipoCupon;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para solicitudes de creación de un cupón.
 *
 * Contiene los datos necesarios para registrar un nuevo cupón en el sistema.
 * Las anotaciones de validación (@NotBlank, @Length, @NotNull, @Future) aseguran que
 * los datos cumplan con los requisitos antes de procesarlos.
 */
public record CrearCuponDTO(

        @NotBlank @Length(min = 3, max = 50) String nombre, // Nombre del cupón, obligatorio, entre 3 y 50 caracteres
        @NotNull float descuento,                           // Porcentaje o valor de descuento, obligatorio
        @NotNull @Future LocalDateTime fechaVencimiento,    // Fecha de vencimiento del cupón, obligatorio y debe ser futura
        @NotNull TipoCupon tipoCupon                        // Tipo de cupón (ej. DESCUENTO, ENVIO), obligatorio
) {
}
