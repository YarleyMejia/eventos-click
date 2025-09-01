package co.edu.uniquindio.proyecto.dto;


public record PagoDTO(
        String moneda,
        String codigoAutorizacion,
        String tipoPago,
        String detalleEstado,
        double valorTransaccion,
        String estado

) {
}
