package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
public record CrearCarritoDTO(
        @NotNull ObjectId idUsuario,
        @NotNull LocalDateTime fecha) {
}
