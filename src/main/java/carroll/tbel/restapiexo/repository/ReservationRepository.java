package carroll.tbel.restapiexo.repository;

import carroll.tbel.restapiexo.models.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
}
