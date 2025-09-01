package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Cuenta;
import co.edu.uniquindio.proyecto.modelo.documentos.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository: Esto es para indicar que la clase define un depósito de datos.
@Repository
public interface OrdenRepo extends MongoRepository<Orden, String> {

   @Query("{id: ?0}")
   Orden buscaOrden(String idOrden);

   //Orden buscaOrden(String idOrden);
}
