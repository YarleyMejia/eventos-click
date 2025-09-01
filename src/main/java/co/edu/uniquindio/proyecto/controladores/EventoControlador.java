package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.response.EventoResponseDto;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEditadoException;
import co.edu.uniquindio.proyecto.servicios.implementacion.EventoServicioImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/eventos")
public class EventoControlador {

    private final EventoServicioImpl eventoServicio;

    @PostMapping("/crear")
    public String createEvent(@RequestBody CrearEventoDTO evento, @RequestHeader("Authorization")String token){
        return eventoServicio.crearEvento(evento, token);

    }

    @PutMapping("/editar")
    public String editEvent(@RequestBody EditarEventoDTO editarEventoDTO,
                            @RequestHeader("Authorization") String token) {
        return eventoServicio.editarEvento(editarEventoDTO, token);
    }

    @DeleteMapping("/eliminar/{id}")
    public String deleteEvent(@PathVariable String id, @RequestHeader("Authorization") String token) {
        return eventoServicio.eliminarEvento(id, token);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformacionEventoDTO> getEventInfo(@PathVariable String id) {
        InformacionEventoDTO infoEvento = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(infoEvento);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<EventoResponseDto>> getAllEvents() {
        List<EventoResponseDto> eventos = eventoServicio.obtenerEventos();
        return ResponseEntity.ok(eventos);
    }

    @PostMapping("/filtrar")
    public ResponseEntity<List<ItemEventoDTO>> filterEvents(@RequestBody FiltroEventoDTO filtroDTO) {
        List<ItemEventoDTO> eventosFiltrados = eventoServicio.filtrarEventos(filtroDTO);
        return ResponseEntity.ok(eventosFiltrados); //Pendiente el servicio
    }
}
