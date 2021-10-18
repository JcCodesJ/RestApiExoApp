package carroll.tbel.restapiexo.utils;

import carroll.tbel.restapiexo.models.entity.Client;
import carroll.tbel.restapiexo.models.entity.Reservation;
import carroll.tbel.restapiexo.repository.ClientRepository;
import carroll.tbel.restapiexo.repository.ReservationRepository;
import carroll.tbel.restapiexo.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseFiller implements InitializingBean {

    private final ReservationRepository rRep;
    private final ClientRepository cRep;
    private final UserRepository uRep;
    private final PasswordEncoder encoder;

    public DatabaseFiller(ReservationRepository rRep, ClientRepository cRep, UserRepository uRep, PasswordEncoder encoder) {
        this.rRep = rRep;
        this.cRep = cRep;
        this.uRep = uRep;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Client c = new Client();
        c.setName("Freya");
        c.setLastName("Carroll");

        c = cRep.save(c);

        Reservation r1 = new Reservation(0L, "")

    }
}
