package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.modelo.documentos.Cuenta;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/*
MongoRepository es genérico, por lo tanto
debemos definir primero el tipo de dato del documento al que
referencia el repositorio y segundo debe definir el
tipo de dato de su @Id, para nuestro caso será Evento y String respectivamente.
 */

//@Repository: Esto es para indicar que la clase define un depósito de datos.
@Repository
public interface CuentaRepo extends MongoRepository<Cuenta, String> {
    //?0: primer argumento de un método
   // @Query(value = " db.cuentas.findOne({email: email}) ")

 /*
 buscará una cuenta de usuario con el email que llega por parámetro,
 si lo encuentra dentro del objeto Optional estará presente dicha cuenta,
 sino, el Optional estará vacío. Es importante indicar que “email”
debe ser un nombre de un atributo de la clase Cuenta.
  */
 //consulta para verificar un emial:
    @Query("{email: ?0 }")
    Optional<Cuenta> buscaEmail(String email);
    //Optional<Cuenta> findByEmail(String email);

    //consulta para verificar la cedula de un Usuario:
    @Query("{usuario.cedula: ?0 }")
    Optional<Cuenta> buscaCedula(String cedula);

 //consulta para verificar el id de una cuenta:
    @Query("{id: ?0}")
    Optional<Cuenta> findById(String id);

    @Query("{email:  ?0, password:  ?1}")
    Optional<Cuenta> validarDatosAutenticacion(@NotBlank @Email String correo, @NotBlank String password);
}
