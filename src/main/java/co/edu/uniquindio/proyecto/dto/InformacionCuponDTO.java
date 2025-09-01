package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.modelo.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.modelo.enums.TipoCupon;

import java.time.LocalDateTime;

public record InformacionCuponDTO(
        String nombre,
        float descuento,
        String codigo,
        LocalDateTime fechaVencimiento,
        TipoCupon tipo,
        EstadoCupon estado) {
}
