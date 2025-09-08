package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.ItemCuponDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.excepciones.CuponesNoEncontradosException;
import co.edu.uniquindio.proyecto.servicios.interfaces.CuponServicio;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para la gestión de cupones.
 *
 * Proporciona endpoints para listar y consultar cupones.
 */
@RestController // Marca la clase como controlador REST
@RequiredArgsConstructor // Genera constructor con los campos final automáticamente
@RequestMapping("/api/cupones") // Prefijo común para todos los endpoints del controlador
public class CuponControlador {

    // Servicio que contiene la lógica de negocio de cupones
    private final CuponServicio cuponServicio;

    /**
     * Endpoint para listar todos los cupones, sin importar su estado.
     *
     * @return MensajeDTO con la lista de ItemCuponDTO
     * @throws CuponesNoEncontradosException si no se encuentran cupones
     */
    @GetMapping("/listar-todos")
    @Operation(
            summary = "Todos los Cupones",
            description = "Permite obtener todos los cupones independientemente de su estado"
    )
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCuponesTodos() throws CuponesNoEncontradosException {
        // Se construye el cuerpo de la respuesta usando MensajeDTO
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cuponServicio.listarCupones()));
    }
}
