package carroll.tbel.restapiexo.repository;

import carroll.tbel.restapiexo.models.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
