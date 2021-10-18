package carroll.tbel.restapiexo.models.dto;

import carroll.tbel.restapiexo.models.entity.Reservation;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ReservationDTO {

    private long resId;
    private String arrivalDate;
    private String checkoutDate;
    private Boolean breakfastIncluded;
    private List<ReservationDTO.ClientDTO> client;

    @Data
    @Builder
    public static class ClientDTO {
        private long id;
        private String name;
        private String lastName;
    }

}
