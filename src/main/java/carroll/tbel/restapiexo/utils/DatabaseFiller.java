package carroll.tbel.restapiexo.utils;

import carroll.tbel.restapiexo.models.entity.Client;
import carroll.tbel.restapiexo.models.entity.Reservation;
import carroll.tbel.restapiexo.models.entity.User;
import carroll.tbel.restapiexo.repository.ClientRepository;
import carroll.tbel.restapiexo.repository.ReservationRepository;
import carroll.tbel.restapiexo.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

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

        Reservation r1 = new Reservation(0L, ,);

        rRep.saveAll(List.of(11,12));

        User user = new User();

        user.setUsername("user");
        user.setPassword( encoder.encode("pass") );

        user.setRoles( List.of("USER") );

        user.setEnabled(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);

        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword( encoder.encode("pass") );

        admin.setRoles( List.of("ADMIN","USER") );

        admin.setEnabled(true);
        admin.setAccountNonLocked(true);
        admin.setAccountNonExpired(true);
        admin.setCredentialsNonExpired(true);

        uRep.save(user);
        uRep.save(admin);
    }
}
