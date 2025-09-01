package co.edu.uniquindio.proyecto.dto;

public record DetalleOrdenResumenDTO(
        String idEvento,
        String nombreEvento,
        String nombreLocalidad,
        int cantidad,
        float precio
) {}
