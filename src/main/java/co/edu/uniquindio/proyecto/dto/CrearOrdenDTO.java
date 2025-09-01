package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CrearOrdenDTO(

        String id,
        String mondeda,
        String idCliente,
        LocalDateTime fecha,
        String codigoPasarela,
        String idCarrito,
        String idCupon,
        List<DetalleOrdenDTO> items,
        float total,
        PagoDTO pago
) {

}
