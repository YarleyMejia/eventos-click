package co.edu.uniquindio.proyecto.servicios.interfaces;

/*
La idea es que esta interface tenga la definición de los métodos
que deben ser implementados con su respectiva lógica de negocio.
 */

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

/*
UsuarioServicio es una interface dice que métodos deben ser implementados más adelante
La idea es que esta interface tenga la definición de los métodos (sin implementación) que deben ser
implementados con su respectiva lógica de negocio.
 */

public interface UsuarioServicio {

    void crear(CrearUsuarioDTO cuenta) throws Exception;
    void eliminar(String id) throws Exception;
    void editar(String id, EditarUsuarioDTO editarUsuarioDTO) throws Exception;
    UsuarioDTO obtener(String id) throws Exception;
    //List<InfoReporteDTO> obtenerReportesUsuario(String id);
    List<UsuarioDTO> listarTodos(String nombre, String ciudad, int pagina);
    //void enviarCodigoVerificacion(EnviarCodigoDTO enviarCoditoDTO) throws Exception;
    void cambiarPassword(CambiarPasswordDTO cambiarPasswordDTO) throws Exception;
    //void activarCuenta(ActivarCuentaDTO activarCuentaDTO) throws Exception;

}

