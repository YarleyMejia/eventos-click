package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoEvento;
import java.time.LocalDateTime;

public record InformacionEventoDTO(
        String urlImagenPoster,
        String direccion,
        String ciudad,
        LocalDateTime fecha,
        TipoEvento tipoEvento,
        EstadoEvento disponibilidad,
        String descripcion
) {
}
