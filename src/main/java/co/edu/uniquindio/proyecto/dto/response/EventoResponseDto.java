package co.edu.uniquindio.proyecto.dto.response;

import lombok.*;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para responder con información básica de un evento.
 *
 * Contiene datos que se envían al cliente al listar o consultar eventos:
 * - urlImagenPoster: URL de la imagen o póster del evento
 * - nombre: Nombre del evento
 * - fecha: Fecha y hora del evento
 * - direccion: Dirección o lugar donde se realiza el evento
 */
@Getter // Genera automáticamente getters para todos los atributos
@Setter // Genera automáticamente setters para todos los atributos
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
@Builder // Permite construir instancias del DTO usando el patrón Builder
public class EventoResponseDto {

    private String urlImagenPoster; // URL de la imagen o póster del evento
    private String nombre;          // Nombre del evento
    private LocalDateTime fecha;    // Fecha y hora del evento
    private String direccion;       // Dirección del evento
}
