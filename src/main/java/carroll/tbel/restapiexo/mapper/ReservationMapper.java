package carroll.tbel.restapiexo.mapper;

import carroll.tbel.restapiexo.models.dto.ClientDTO;
import carroll.tbel.restapiexo.models.dto.ReservationDTO;
import carroll.tbel.restapiexo.models.entity.Client;
import carroll.tbel.restapiexo.models.entity.Reservation;
import carroll.tbel.restapiexo.models.forms.ReservationForm;
import carroll.tbel.restapiexo.models.forms.ReservationUpdateForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation entity){

        if( entity == null )
            return null;

        return ReservationDTO.builder()
                .resId( entity.getResId() )
                .arrivalDate( entity.getArrivalDate() )
                .checkoutDate( entity.getCheckoutDate() )
                .breakfastIncluded( entity.getBreakfastIncluded() )
                .build();
    }

    public Reservation formToEntity(ReservationForm form){
        if (form == null)
            return null;

        Reservation reservation = new Reservation();
        reservation.setResId(form.getResID());
        reservation.setArrivalDate(form.getArrivalDate());
        reservation.setCheckoutDate(form.getCheckoutDate());
        reservation.setBreakfastIncluded(form.getBreakfastIncluded());

        return reservation;
    }

    public Reservation updateFormToEntity(ReservationUpdateForm form){
        if (form == null)
            return null;

        Reservation reservation = new Reservation();
        reservation.setArrivalDate(form.getArrivalDate());
        reservation.setCheckoutDate(form.getCheckoutDate());
        reservation.setBreakfastIncluded(form.getBreakfastIncluded());

        return reservation;
    }


    private ReservationDTO.ClientDTO toInnerDTO(Client client){
        if (client == null)
            return null;

        return ReservationDTO.ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .build();
    }

}
