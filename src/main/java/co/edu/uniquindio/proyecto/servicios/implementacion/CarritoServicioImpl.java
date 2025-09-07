/*
package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;
import co.edu.uniquindio.proyecto.modelo.documentos.Carrito;
import co.edu.uniquindio.proyecto.repositorios.CarritoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CarritoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarritoServicioImpl implements CarritoServicio {

    private final CarritoRepo carritoRepo;
    @Override
    public String crearCarrito(CrearCarritoDTO crearCarritoDTO) throws CarritoNoCreadoException {
        try {
            Carrito carrito = new Carrito();
            carrito.setIdUsuario( crearCarritoDTO.idUsuario() );
            carrito.setFecha( crearCarritoDTO.fecha() );

            carritoRepo.save(carrito);

            return "Carrito creado exitosamente.";
        } catch (Exception e){
            throw new CarritoNoCreadoException("El carrito no fue creado. " + e.getMessage());
        }
    }
}

 */

package co.edu.uniquindio.proyecto.servicios.implementacion;

import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;
import co.edu.uniquindio.proyecto.modelo.documentos.Carrito;
import co.edu.uniquindio.proyecto.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.proyecto.repositorios.CarritoRepo;
import co.edu.uniquindio.proyecto.servicios.interfaces.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoServicioImpl implements CarritoServicio {

    @Autowired
    private CarritoRepo carritoRepo;

    @Override
    public String crearCarrito(CrearCarritoDTO carritoDTO) throws CarritoNoCreadoException {
        try {
            Carrito carrito = new Carrito();
            carrito.setIdUsuario(carritoDTO.getIdUsuario());
            carrito.setFecha(carritoDTO.getFecha());
            carrito.setItems(carritoDTO.getItems() != null ? carritoDTO.getItems() : List.of());

            carritoRepo.save(carrito);
            return "Carrito creado exitosamente.";
        } catch (Exception e) {
            throw new CarritoNoCreadoException("No se pudo crear el carrito: " + e.getMessage());
        }
    }
}
