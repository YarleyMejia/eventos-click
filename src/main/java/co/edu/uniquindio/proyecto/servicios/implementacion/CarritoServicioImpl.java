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
