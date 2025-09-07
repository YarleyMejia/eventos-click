
package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.Entity.UserEntity;
import co.edu.uniquindio.proyecto.configuracion.security.JwtService;
import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.response.EventoResponseDto;
import co.edu.uniquindio.proyecto.excepciones.EventoNoCreadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEditadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEliminadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEncontradoException;
import co.edu.uniquindio.proyecto.excepciones.dataError.InvalidTokenException;
import co.edu.uniquindio.proyecto.modelo.documentos.Evento;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.repositorios.EventoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final JwtService jwtService;
    private final EventoRepo eventoRepo;

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO, String token) throws EventoNoCreadoException {

        String roleFromToken = jwtService.getRoleFromToken(token);

        if (!"admin".equals(roleFromToken)) {
            throw new InvalidTokenException("No tiene permiso para crear eventos");
        }

        try {
            if(crearEventoDTO.fechaEvento().isBefore(LocalDateTime.now())){
                throw new EventoNoCreadoException("La fecha ingresada para el evento debe ser mayor a la fecha actual");
            }

            if( existeEvento(crearEventoDTO.fechaEvento(), crearEventoDTO.nombre(), crearEventoDTO.ciudad())){

                throw new EventoNoCreadoException("Ya existe un evento registrado con el nombre " +
                        crearEventoDTO.nombre() + " para la fecha " + crearEventoDTO.fechaEvento());
            }

            Evento nuevoEvento = new Evento();
            nuevoEvento.setFechaEvento(crearEventoDTO.fechaEvento());
            nuevoEvento.setNombre(crearEventoDTO.nombre());
            nuevoEvento.setDescripcion(crearEventoDTO.descripcion());
            nuevoEvento.setEstado(EstadoEvento.ACTIVO);
            nuevoEvento.setTipo(crearEventoDTO.tipoEvento());
            nuevoEvento.setCiudad(crearEventoDTO.ciudad());
            nuevoEvento.setLocalidades(crearEventoDTO.localidades()
            );

            eventoRepo.save(nuevoEvento);

            return "El evento ha sido creado con éxito.";
        }catch (Exception e){
            throw new EventoNoCreadoException("Error al tratar de crear el evento." + e.getMessage());
        }
    }


    /*public String crearEvento(CrearEventoDTO crearEventoDTO, String token) throws EventoNoCreadoException {
        // Si no hay token, dejamos pasar en modo pruebas
        if (token == null) {
            // 👇 Opcional: podrías saltar validación solo en perfil "test"
            return crearEventoInterno(crearEventoDTO);
        }

        String roleFromToken = jwtService.getRoleFromToken(token);

        if (!"admin".equals(roleFromToken)) {
            throw new InvalidTokenException("No tiene permiso para crear eventos");
        }

        return crearEventoInterno(crearEventoDTO);
    }

    private String crearEventoInterno(CrearEventoDTO crearEventoDTO) throws EventoNoCreadoException {
        try {
            if (crearEventoDTO.fechaEvento().isBefore(LocalDateTime.now())) {
                throw new EventoNoCreadoException("La fecha ingresada para el evento debe ser mayor a la fecha actual");
            }

            if (existeEvento(crearEventoDTO.fechaEvento(), crearEventoDTO.nombre(), crearEventoDTO.ciudad())) {
                throw new EventoNoCreadoException("Ya existe un evento registrado con el nombre " +
                        crearEventoDTO.nombre() + " para la fecha " + crearEventoDTO.fechaEvento());
            }

            Evento nuevoEvento = new Evento();
            nuevoEvento.setFechaEvento(crearEventoDTO.fechaEvento());
            nuevoEvento.setNombre(crearEventoDTO.nombre());
            nuevoEvento.setDescripcion(crearEventoDTO.descripcion());
            nuevoEvento.setEstado(EstadoEvento.ACTIVO);
            nuevoEvento.setTipo(crearEventoDTO.tipoEvento());
            nuevoEvento.setCiudad(crearEventoDTO.ciudad());
            nuevoEvento.setLocalidades(crearEventoDTO.localidades());

            eventoRepo.save(nuevoEvento);

            return "El evento ha sido creado con éxito.";
        } catch (Exception e) {
            throw new EventoNoCreadoException("Error al tratar de crear el evento." + e.getMessage());
        }
    }
*/

    @Override
    public String editarEvento(EditarEventoDTO editarEventoDTO, String token) throws EventoNoEditadoException {

        String roleFromToken = jwtService.getRoleFromToken(token);

        if (!"admin".equals(roleFromToken)) {
            throw new InvalidTokenException("No tiene permiso para crear eventos");
        }

        try {
            Evento eventoModificado = obtenerEvento(editarEventoDTO.id());

            if(editarEventoDTO.fechaEvento().isBefore(LocalDateTime.now())){
                throw new Exception("La nueva fecha ingresada para el evento debe ser mayor a la fecha actual");
            }

            eventoModificado.setImagenPortada(editarEventoDTO.imagenPortada());
            eventoModificado.setEstado(editarEventoDTO.estado());
            eventoModificado.setDescripcion(editarEventoDTO.descripcion());
            eventoModificado.setImagenLocalidades(editarEventoDTO.imagenLocalidades());
            eventoModificado.setFechaEvento(editarEventoDTO.fechaEvento());

            eventoRepo.save(eventoModificado);
            return "Evento editado exitosamente";
        } catch(Exception e){
            throw new EventoNoEditadoException("Error al crear el evento." +e.getMessage());
        }
    }

    @Override
    public String eliminarEvento(String id, String token) throws EventoNoEliminadoException {

        String roleFromToken = jwtService.getRoleFromToken(token);

        if (!"admin".equals(roleFromToken)) {
            throw new InvalidTokenException("No tiene permiso para crear eventos");
        }

        try {
            Evento evento = obtenerEvento(id);

            evento.setEstado(EstadoEvento.INACTIVO);

            eventoRepo.save(evento);

            return "El evento ha sido eliminado.";
        } catch (Exception e){
            throw new EventoNoEliminadoException("Evento no fue eliminado." + e.getMessage());
        }
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id)  throws EventoNoEncontradoException {
        try {
            Evento evento = obtenerEvento(id);

            return new InformacionEventoDTO(
                    evento.getImagenPortada(),
                    evento.getDireccion(),
                    evento.getCiudad(),
                    evento.getFechaEvento(),
                    evento.getTipo(),
                    evento.getEstado(),
                    evento.getDescripcion()
            );
        } catch(Exception e){
            throw new EventoNoEncontradoException("Error al cargar los detalles del evento." +e.getMessage());
        }
    }

    @Override
    public List<EventoResponseDto> obtenerEventos() {
        List<Evento> eventos = eventoRepo.findAll();
        List<EventoResponseDto> eventosResponse = new ArrayList<>();

        for (Evento evento : eventos) {
            EventoResponseDto eventoResponse = new EventoResponseDto();
            eventoResponse.setUrlImagenPoster(evento.getImagenPortada());
            eventoResponse.setNombre(evento.getNombre());
            eventoResponse.setDireccion(evento.getDireccion());
            eventoResponse.setDireccion(evento.getDireccion());
            eventosResponse.add(eventoResponse);
        }

        return eventosResponse;
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroDTO) {
        return null;
    }

    private boolean existeEvento(LocalDateTime fechaEvento, String nombre, String ciudad) {

        return eventoRepo.buscarEvento(nombre, fechaEvento, ciudad).isPresent();

    }

    private Evento obtenerEvento(String id) throws Exception {

        Optional<Evento> eventoOptional = eventoRepo.findById(id);

        if(eventoOptional.isEmpty()){
            throw new Exception("No existe un evento registrado con el id " + id + ".");
        }

        return eventoOptional.get();

    }
}

/*
// versión sin token para pruebas

package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.dto.response.EventoResponseDto;
import co.edu.uniquindio.proyecto.excepciones.EventoNoCreadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEditadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEliminadoException;
import co.edu.uniquindio.proyecto.excepciones.EventoNoEncontradoException;
import co.edu.uniquindio.proyecto.modelo.documentos.Evento;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoEvento;
import co.edu.uniquindio.proyecto.repositorios.EventoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.EventoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventoServicioImpl implements EventoServicio {

    private final EventoRepo eventoRepo;

    @Override
    public String crearEvento(CrearEventoDTO crearEventoDTO, String token) throws EventoNoCreadoException {

        // Para pruebas locales: asumimos rol admin
        String roleFromToken = "admin";

        if (!"admin".equals(roleFromToken)) {
            throw new RuntimeException("No tiene permiso para crear eventos");
        }

        try {
            if (crearEventoDTO.fechaEvento().isBefore(LocalDateTime.now())) {
                throw new EventoNoCreadoException("La fecha ingresada para el evento debe ser mayor a la fecha actual");
            }

            if (existeEvento(crearEventoDTO.fechaEvento(), crearEventoDTO.nombre(), crearEventoDTO.ciudad())) {
                throw new EventoNoCreadoException("Ya existe un evento registrado con el nombre " +
                        crearEventoDTO.nombre() + " para la fecha " + crearEventoDTO.fechaEvento());
            }

            Evento nuevoEvento = new Evento();
            nuevoEvento.setFechaEvento(crearEventoDTO.fechaEvento());
            nuevoEvento.setNombre(crearEventoDTO.nombre());
            nuevoEvento.setDescripcion(crearEventoDTO.descripcion());
            nuevoEvento.setEstado(EstadoEvento.ACTIVO);
            nuevoEvento.setTipo(crearEventoDTO.tipoEvento());
            nuevoEvento.setCiudad(crearEventoDTO.ciudad());
            nuevoEvento.setLocalidades(crearEventoDTO.localidades());

            eventoRepo.save(nuevoEvento);

            return "El evento ha sido creado con éxito.";
        } catch (Exception e) {
            throw new EventoNoCreadoException("Error al tratar de crear el evento." + e.getMessage());
        }
    }

    @Override
    public String editarEvento(EditarEventoDTO editarEventoDTO, String token) throws EventoNoEditadoException {

        // Para pruebas locales: asumimos rol admin
        String roleFromToken = "admin";

        if (!"admin".equals(roleFromToken)) {
            throw new RuntimeException("No tiene permiso para editar eventos");
        }

        try {
            Evento eventoModificado = obtenerEvento(editarEventoDTO.id());

            if (editarEventoDTO.fechaEvento().isBefore(LocalDateTime.now())) {
                throw new Exception("La nueva fecha ingresada para el evento debe ser mayor a la fecha actual");
            }

            eventoModificado.setImagenPortada(editarEventoDTO.imagenPortada());
            eventoModificado.setEstado(editarEventoDTO.estado());
            eventoModificado.setDescripcion(editarEventoDTO.descripcion());
            eventoModificado.setImagenLocalidades(editarEventoDTO.imagenLocalidades());
            eventoModificado.setFechaEvento(editarEventoDTO.fechaEvento());

            eventoRepo.save(eventoModificado);
            return "Evento editado exitosamente";
        } catch (Exception e) {
            throw new EventoNoEditadoException("Error al editar el evento." + e.getMessage());
        }
    }

    @Override
    public String eliminarEvento(String id, String token) throws EventoNoEliminadoException {

        // Para pruebas locales: asumimos rol admin
        String roleFromToken = "admin";

        if (!"admin".equals(roleFromToken)) {
            throw new RuntimeException("No tiene permiso para eliminar eventos");
        }

        try {
            Evento evento = obtenerEvento(id);
            evento.setEstado(EstadoEvento.INACTIVO);
            eventoRepo.save(evento);

            return "El evento ha sido eliminado.";
        } catch (Exception e) {
            throw new EventoNoEliminadoException("Evento no fue eliminado." + e.getMessage());
        }
    }

    @Override
    public InformacionEventoDTO obtenerInformacionEvento(String id) throws EventoNoEncontradoException {
        try {
            Evento evento = obtenerEvento(id);

            return new InformacionEventoDTO(
                    evento.getImagenPortada(),
                    evento.getDireccion(),
                    evento.getCiudad(),
                    evento.getFechaEvento(),
                    evento.getTipo(),
                    evento.getEstado(),
                    evento.getDescripcion()
            );
        } catch (Exception e) {
            throw new EventoNoEncontradoException("Error al cargar los detalles del evento." + e.getMessage());
        }
    }

    @Override
    public List<EventoResponseDto> obtenerEventos() {
        List<Evento> eventos = eventoRepo.findAll();
        List<EventoResponseDto> eventosResponse = new ArrayList<>();

        for (Evento evento : eventos) {
            EventoResponseDto eventoResponse = new EventoResponseDto();
            eventoResponse.setUrlImagenPoster(evento.getImagenPortada());
            eventoResponse.setNombre(evento.getNombre());
            eventoResponse.setDireccion(evento.getDireccion());
            eventosResponse.add(eventoResponse);
        }

        return eventosResponse;
    }

    @Override
    public List<ItemEventoDTO> filtrarEventos(FiltroEventoDTO filtroDTO) {
        return null; // Pendiente implementación
    }

    private boolean existeEvento(LocalDateTime fechaEvento, String nombre, String ciudad) {
        return eventoRepo.buscarEvento(nombre, fechaEvento, ciudad).isPresent();
    }

    private Evento obtenerEvento(String id) throws Exception {
        Optional<Evento> eventoOptional = eventoRepo.findById(id);

        if (eventoOptional.isEmpty()) {
            throw new Exception("No existe un evento registrado con el id " + id + ".");
        }

        return eventoOptional.get();
    }
}
*/
