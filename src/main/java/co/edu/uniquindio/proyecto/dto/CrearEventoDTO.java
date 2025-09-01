package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.modelo.enums.TipoEvento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import co.edu.uniquindio.proyecto.modelo.vo.Localidad;
import java.time.LocalDateTime;
import java.util.List;

/*
los DTO son usados para enviar datos que la capa de presentación(GUI)
 necesita mostrar en la interfaz de usuario.
 */
public record CrearEventoDTO(
        @NotBlank @Length (max = 20) String ciudad,
        @NotBlank String imagenPortada,
        @NotBlank String imagenLocalidades,
        @NotBlank @Length (min = 5, max = 100) String nombre,
        @NotBlank @Length (max = 500) String descripcion,
        @NotBlank @Length (max = 100)String direccion,
        @NotNull TipoEvento tipoEvento,
        @NotBlank LocalDateTime fechaEvento,
        @NotBlank List<Localidad> localidades,// ID de la Localidad donde se realiza el evento
        @NotNull EstadoEvento estado // Estado inicial del evento
) {

}
