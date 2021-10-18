package carroll.tbel.restapiexo.models.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Table(name = "reservation")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @Column (name = "Reservation Id", nullable = false)
    private long resId;

    @Column (nullable = false)
    private LocalDateTime arrivalDate;

    @Column (nullable = false)
    private LocalDateTime checkoutDate;

    @Getter
    private Boolean breakfastIncluded;

    @ManyToOne
    private Client client;


}
