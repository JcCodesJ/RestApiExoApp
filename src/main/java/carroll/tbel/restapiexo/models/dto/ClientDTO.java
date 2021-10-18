package carroll.tbel.restapiexo.models.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDTO {

    private long id;
    private String name;
    private String lastName;
    private List<ClientDTO.ReservationDTO> reservations;


    public static class ReservationDTO{

        private long resID;
        private String arrivalDate;
        private String checkoutDate;
        private Boolean breakfastIncluded;
    }

}
