package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.response.EventoResponseDto;
import co.edu.uniquindio.proyecto.excepciones.EventoNoCreadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEditadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEliminadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEncontradoException;

import java.util.List;
/*
La idea es tener un servicio por cada Colleción
 */
public interface EventoServicio {
    String crearEvento(CrearEventoDTO crearEventoDTO, String token) throws EventoNoCreadoException;//solo administrador

    String editarEvento(EditarEventoDTO editarEventoDTO, String token) throws EventoNoEditadoException;

    String eliminarEvento(String id, String token) throws EventoNoEliminadoException;

    InformacionEventoDTO obtenerInformacionEvento(String id) throws EventoNoEncontradoException;

    List <EventoResponseDto> obtenerEventos();

    List <ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroDTO);//clinteFinal
}
