package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Cuenta;
import co.edu.uniquindio.proyecto.modelo.documentos.Cupon;
import co.edu.uniquindio.proyecto.modelo.enums.EstadoCupon;
import co.edu.uniquindio.proyecto.modelo.enums.TipoCupon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CuponRepo extends MongoRepository<Cupon, String> {
    Optional<List<Cupon>> findAllByEstado(EstadoCupon estado);
    Optional<List<Cupon>> findAllByNombreRegexAndEstado(String nombre, EstadoCupon estado);
    Optional<List<Cupon>> findAllByDescuentoAndEstado(float descuento, EstadoCupon estado);
    Optional<List<Cupon>> findAllByFechaVencimientoAndEstado(LocalDateTime fechaVencimiento, EstadoCupon estado);
    Optional<List<Cupon>> findAllByEstadoAndTipo(EstadoCupon estado, TipoCupon tipoCupon);

}
