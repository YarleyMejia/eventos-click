/*
package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
public record CrearCarritoDTO(
        @NotNull ObjectId idUsuario,
        @NotNull LocalDateTime fecha) {
}

 */

package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.vo.DetalleCarrito;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrearCarritoDTO {
    private ObjectId IdUsuario;
    private LocalDateTime fecha;
    private List<DetalleCarrito> items; // Para enviar los detalles del carrito
}


