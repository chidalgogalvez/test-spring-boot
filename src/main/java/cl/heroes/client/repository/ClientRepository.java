package cl.heroes.client.repository;

import cl.heroes.client.entity.Client;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

  Optional<Client> findByRut(String rut);

  Optional<Client> findByEmail(String email);

  void deleteByRut(String rut);
}
