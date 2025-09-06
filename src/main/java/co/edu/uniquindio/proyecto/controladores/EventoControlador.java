package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.modelo.documentos.Evento;
import co.edu.uniquindio.proyecto.servicios.interfaces.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoControlador {

    private final EventoService eventoService;

    @PostMapping("/crear")
    public ResponseEntity<Evento> crear(@RequestBody Evento evento){
        Evento creado = eventoService.crearEvento(evento);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Evento>> todos(){
        return ResponseEntity.ok(eventoService.obtenerEventos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> porId(@PathVariable String id){
        Evento e = eventoService.obtenerPorId(id);
        if (e == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(e);
    }
}
