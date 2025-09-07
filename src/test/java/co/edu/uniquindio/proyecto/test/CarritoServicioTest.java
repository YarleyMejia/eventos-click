package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.dto.CrearCarritoDTO;
import co.edu.uniquindio.proyecto.excepciones.CarritoNoCreadoException;
import co.edu.uniquindio.proyecto.modelo.vo.DetalleCarrito;
import co.edu.uniquindio.proyecto.servicios.interfaces.CarritoServicio;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CarritoServicioTest {

    @Autowired
    private CarritoServicio carritoServicio;
    @Test
    public void crearCarritoTest() throws CarritoNoCreadoException {

        List<DetalleCarrito> items = new ArrayList<>();
        ObjectId objectUsuario = new ObjectId("66e5c4b6e1ef425df559016d");
        ObjectId objectEvento = new ObjectId("66e5c4b6e1ef425df559016d");
        DetalleCarrito detalle = new DetalleCarrito(objectEvento, 4, "VIP");
        items.add(detalle);

        CrearCarritoDTO carritoDTO = new CrearCarritoDTO(
                objectUsuario,
                LocalDateTime.of(2024, 9, 14, 15, 30),
                items
        );

        String mensaje = carritoServicio.crearCarrito(carritoDTO);
        Assertions.assertEquals( "Carrito creado exitosamente.", mensaje);
    }
}
