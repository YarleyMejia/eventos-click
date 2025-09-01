package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.Entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserEntity, Long> {

    @Override
    Optional<UserEntity> findById(Long idUser);

    UserEntity findByEmail(String email);
}
