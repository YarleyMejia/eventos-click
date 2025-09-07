package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.Entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends MongoRepository<RoleEntity, String> {
}
