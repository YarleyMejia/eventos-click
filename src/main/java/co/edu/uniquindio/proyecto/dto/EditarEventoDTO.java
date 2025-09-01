package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record EditarEventoDTO(
        String id,
        @NotBlank String imagenPortada,
        @NotBlank String imagenLocalidades,
        @NotBlank @Length(max = 500) String descripcion,
        @NotNull EstadoEvento estado,
        String direccion,
        String ciudad,
        LocalDateTime fechaEvento,
        String nombre
) {
}
