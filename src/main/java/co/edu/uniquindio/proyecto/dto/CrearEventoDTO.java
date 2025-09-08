package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import co.edu.uniquindio.proyecto.modelo.vo.Localidad;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) para solicitudes de creación de un evento.
 *
 * Este DTO se utiliza para enviar la información necesaria al backend
 * cuando se quiere crear un nuevo evento. Se aplican validaciones para
 * asegurar que los datos enviados sean correctos antes de procesarlos.
 *
 * Uso: Los DTOs se usan para transferir datos entre la capa de presentación (GUI)
 * y la capa de servicios del backend.
 */
public record CrearEventoDTO(

        @NotBlank @Length(max = 20) String ciudad,           // Ciudad donde se realizará el evento, obligatorio, máximo 20 caracteres
        @NotBlank String imagenPortada,                      // URL o path de la imagen principal del evento, obligatorio
        @NotBlank String imagenLocalidades,                  // URL o path de la imagen de localidades, obligatorio
        @NotBlank @Length(min = 5, max = 100) String nombre, // Nombre del evento, obligatorio, entre 5 y 100 caracteres
        @NotBlank @Length(max = 500) String descripcion,     // Descripción del evento, obligatorio, máximo 500 caracteres
        @NotBlank @Length(max = 100) String direccion,       // Dirección donde se realizará el evento, obligatorio, máximo 100 caracteres
        @NotNull TipoEvento tipoEvento,                      // Tipo de evento (ej. CONCIERTO, TEATRO), obligatorio
        @NotBlank LocalDateTime fechaEvento,                 // Fecha y hora del evento, obligatorio
        @NotBlank List<Localidad> localidades,              // Lista de localidades donde se realizará el evento, obligatorio
        @NotNull EstadoEvento estado                         // Estado inicial del evento (ej. ACTIVO, INACTIVO), obligatorio
) {
}
