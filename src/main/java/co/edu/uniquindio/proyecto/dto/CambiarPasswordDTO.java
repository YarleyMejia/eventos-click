package co.edu.uniquindio.proyecto.dto;

public record CambiarPasswordDTO(
        String email,
        String codigoVerificacion,
        String passwordNueva
) {
}
