package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.models.dto.ReservationDTO;
import carroll.tbel.restapiexo.models.forms.ReservationForm;
import carroll.tbel.restapiexo.models.forms.ReservationUpdateForm;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getAll();
    ReservationDTO getOne(String resId);
    ReservationDTO insert(ReservationForm toInsert);
    ReservationDTO delete(String resId);
    ReservationDTO update(String resId, ReservationUpdateForm form);

}
