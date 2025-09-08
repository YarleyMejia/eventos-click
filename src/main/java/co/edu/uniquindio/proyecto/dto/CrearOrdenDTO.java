package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO (Data Transfer Object) para la creación de una orden de compra.
 *
 * Este DTO se utiliza para enviar al backend toda la información necesaria
 * para registrar una nueva orden de un cliente, incluyendo detalles de pago,
 * carrito y cupones aplicados.
 */
public record CrearOrdenDTO(

        String id,               // ID único de la orden (puede ser generado por el sistema)
        String mondeda,          // Moneda en la que se realiza la orden (ej. COP, USD)
        String idCliente,        // ID del cliente que realiza la orden
        LocalDateTime fecha,     // Fecha y hora de creación de la orden
        String codigoPasarela,   // Código de referencia de la pasarela de pago
        String idCarrito,        // ID del carrito asociado a la orden
        String idCupon,          // ID del cupón aplicado (si existe)
        List<DetalleOrdenDTO> items, // Lista de detalles de cada producto o servicio incluido en la orden
        float total,             // Total de la orden después de descuentos y cupones
        PagoDTO pago             // Información del pago asociado a la orden
) {
}
