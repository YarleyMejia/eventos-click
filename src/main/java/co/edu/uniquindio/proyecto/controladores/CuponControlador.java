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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cupones")
public class CuponControlador {

    private final CuponServicio cuponServicio;

    @GetMapping("/listar-todos")
    @Operation(summary = "Todos los Cupones", description = "Permite obtener todos los cupones independientemente de su estado")
    public ResponseEntity<MensajeDTO<List<ItemCuponDTO>>> listarCuponesTodos() throws CuponesNoEncontradosException {
        return ResponseEntity.ok().body( new MensajeDTO<>( false, cuponServicio.listarCupones()));
    }
}
