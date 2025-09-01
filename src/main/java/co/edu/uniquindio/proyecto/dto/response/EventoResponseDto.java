package co.edu.uniquindio.proyecto.dto.response;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventoResponseDto {

    private String urlImagenPoster;
    private String nombre;
    private LocalDateTime fecha;
    private String direccion;
}
