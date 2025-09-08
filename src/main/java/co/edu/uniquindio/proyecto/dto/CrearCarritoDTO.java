package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.vo.DetalleCarrito;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * DTO (Data Transfer Object) para la creación de un carrito de compras.
 *
 * Este DTO se utiliza para enviar información al backend al crear un carrito, incluyendo:
 * - idUsuario: ID del usuario al que pertenece el carrito
 * - fecha: Fecha y hora de creación del carrito
 * - items: Lista de detalles de los productos agregados al carrito
 */
@Data // Genera getters, setters, toString, equals y hashCode
@Getter // Genera getters
@Setter // Genera setters
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los atributos
public class CrearCarritoDTO {

    private ObjectId IdUsuario;              // ID del usuario dueño del carrito
    private LocalDateTime fecha;             // Fecha y hora de creación del carrito
    private List<DetalleCarrito> items;      // Detalles de los productos en el carrito
}


