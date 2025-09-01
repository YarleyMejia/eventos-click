package co.edu.uniquindio.proyecto.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ItemOrdenDTO(
        String id,
        String idCliente,
        LocalDateTime fecha,
        String codigoPasarela,
        String idCupon,
        float total,
        List<DetalleOrdenResumenDTO> detalles
) {}
