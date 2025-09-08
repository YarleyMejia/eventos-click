package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.response.EventoResponseDto;
import co.edu.uniquindio.proyecto.servicios.implementacion.EventoServicioImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de eventos.
 *
 * Proporciona endpoints para:
 * - Crear, editar y eliminar eventos
 * - Consultar información de un evento
 * - Listar todos los eventos
 * - Filtrar eventos según criterios
 */
@RestController // Marca la clase como controlador REST
@RequiredArgsConstructor // Genera constructor con campos final automáticamente
@RequestMapping("/api/eventos") // Prefijo común para todos los endpoints del controlador
public class EventoControlador {

    // Servicio que contiene la lógica de negocio para eventos
    private final EventoServicioImpl eventoServicio;

    /**
     * Endpoint para crear un nuevo evento.
     * Token deshabilitado temporalmente para pruebas.
     *
     * @param evento DTO con los datos del evento
     * @return Mensaje indicando éxito o error
     */
    @PostMapping("/crear")
    public String createEvent(@RequestBody CrearEventoDTO evento) {
        return eventoServicio.crearEvento(evento, null);
    }

    /**
     * Endpoint para editar un evento existente.
     * Token deshabilitado temporalmente para pruebas.
     *
     * @param editarEventoDTO DTO con los cambios del evento
     * @return Mensaje indicando éxito o error
     */
    @PutMapping("/editar")
    public String editEvent(@RequestBody EditarEventoDTO editarEventoDTO) {
        return eventoServicio.editarEvento(editarEventoDTO, null);
    }

    /**
     * Endpoint para eliminar un evento por su ID.
     * Token deshabilitado temporalmente para pruebas.
     *
     * @param id ID del evento a eliminar
     * @return Mensaje indicando éxito o error
     */
    @DeleteMapping("/eliminar/{id}")
    public String deleteEvent(@PathVariable String id) {
        return eventoServicio.eliminarEvento(id, null);
    }

    /**
     * Endpoint para obtener la información de un evento por su ID.
     *
     * @param id ID del evento
     * @return InformacionEventoDTO con los datos del evento
     */
    @GetMapping("/{id}")
    public ResponseEntity<InformacionEventoDTO> getEventInfo(@PathVariable String id) {
        InformacionEventoDTO infoEvento = eventoServicio.obtenerInformacionEvento(id);
        return ResponseEntity.ok(infoEvento);
    }

    /**
     * Endpoint para listar todos los eventos.
     *
     * @return Lista de EventoResponseDto con todos los eventos
     */
    @GetMapping("/todos")
    public ResponseEntity<List<EventoResponseDto>> getAllEvents() {
        List<EventoResponseDto> eventos = eventoServicio.obtenerEventos();
        return ResponseEntity.ok(eventos);
    }

    /**
     * Endpoint para filtrar eventos según criterios proporcionados.
     *
     * @param filtroDTO DTO con los criterios de filtrado
     * @return Lista de ItemEventoDTO con los eventos que cumplen los criterios
     */
    @PostMapping("/filtrar")
    public ResponseEntity<List<ItemEventoDTO>> filterEvents(@RequestBody FiltroEventoDTO filtroDTO) {
        List<ItemEventoDTO> eventosFiltrados = eventoServicio.filtrarEventos(filtroDTO);
        return ResponseEntity.ok(eventosFiltrados);
    }
}
