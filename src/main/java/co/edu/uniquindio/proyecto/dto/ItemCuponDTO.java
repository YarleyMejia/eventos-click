package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.TipoCupon;

import java.time.LocalDateTime;

public record ItemCuponDTO(
        String id,
        String nombre,
        float descuento,
        String codigo,
        LocalDateTime fechaVencimiento) {
}
