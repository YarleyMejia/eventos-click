
package co.edu.uniquindio.proyecto.servicios.implementacion;


import co.edu.uniquindio.proyecto.modelo.documentos.Evento;
import co.edu.uniquindio.proyecto.repositorios.EventoRepo;
//import co.edu.uniquindio.proyecto.servicios.implementacion;
import co.edu.uniquindio.proyecto.servicios.interfaces.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoService {

    private final EventoRepo eventoRepo;

    @Override
    public Evento crearEvento(Evento evento) {
        return eventoRepo.save(evento);
    }

    @Override
    public List<Evento> obtenerEventos() {
        return eventoRepo.findAll();
    }

    @Override
    public Evento obtenerPorId(String id) {
        return eventoRepo.findById(id).orElse(null);
    }
}
