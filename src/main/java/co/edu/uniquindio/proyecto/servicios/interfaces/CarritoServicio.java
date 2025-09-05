package co.edu.uniquindio.proyecto.servicios.interfaces;

/*
import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;

public interface CarritoServicio {

    String crearCarrito(CrearCarritoDTO crearCarritoDTO) throws CarritoNoCreadoException;
}

 */


import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;

public interface CarritoServicio {

    /**
     * Crea un carrito en MongoDB a partir del DTO proporcionado
     * @param carritoDTO datos del carrito
     * @return mensaje de éxito
     * @throws CarritoNoCreadoException si ocurre algún error al guardar
     */
    String crearCarrito(CrearCarritoDTO carritoDTO) throws CarritoNoCreadoException;
}

