package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.excepciones.*;
import co.edu.uniquindio.proyecto.modelo.documentos.Cupon;
import co.edu.uniquindio.proyecto.modelo.enums.TipoCupon;

import java.time.LocalDateTime;
import java.util.List;

public interface CuponServicio {

    String crearCupon(CrearCuponDTO cupon) throws CuponNoCreadoException;
    Cupon editarCupon(EditarCuponDTO cupon) throws CuponNoActualizadoException, CuponNoEncontradoException;
    String eliminarCupon(String id) throws CuponNoEliminadoException;
    InformacionCuponDTO obtenerInformacionCupon(String id) throws CuponNoEncontradoException;
    String enviarCodigoCuponPersonal(String correo) throws Exception;
    String enviarCodigoCuponATodos() throws Exception;
    List<ItemCuponDTO> listarCupones() throws CuponesNoEncontradosException;
    List<ItemCuponDTO> listarCuponesDisponibles() throws CuponesNoEncontradosException;
    List<ItemCuponDTO> listarCuponesPorNombre(String nombre) throws CuponesNoEncontradosException;
    List<ItemCuponDTO> listarCuponesPorDescuento(float descuento) throws CuponesNoEncontradosException;
    List<ItemCuponDTO> listarCuponesPorFechaVencimiento(LocalDateTime fechaVencimiento) throws CuponesNoEncontradosException;
    List<ItemCuponDTO> listarCuponesPorTipo(TipoCupon tipoCupon) throws CuponesNoEncontradosException;

}
