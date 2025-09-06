package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.modelo.documentos.Evento;

import java.util.List;

public interface EventoServicio {
    Evento crearEvento(Evento evento);
    List<Evento> obtenerEventos();
    Evento obtenerPorId(String id);
}
