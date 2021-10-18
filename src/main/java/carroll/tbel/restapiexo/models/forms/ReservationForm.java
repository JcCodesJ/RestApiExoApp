package carroll.tbel.restapiexo.models.forms;

import jdk.jfr.BooleanFlag;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Validated
public class ReservationForm {

    private long resID;

    @NotBlank
    private String arrivalDate;

    @NotBlank
    private String checkoutDate;

    @BooleanFlag
    private Boolean breakfastIncluded;

    @NotEmpty
    private List<Long> reservationIds;
}
