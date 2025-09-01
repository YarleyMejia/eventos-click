package co.edu.uniquindio.proyecto.servicios.interfaces;

import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;

public interface CarritoServicio {

    String crearCarrito(CrearCarritoDTO crearCarritoDTO) throws CarritoNoCreadoException;
}
