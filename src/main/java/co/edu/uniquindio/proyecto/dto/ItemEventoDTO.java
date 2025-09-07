package co.edu.uniquindio.proyecto.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

public record ItemEventoDTO(
        String urlImagenPoster,
        String nombre,
        LocalDateTime fecha,
        String direccion
) {
}
