package carroll.tbel.restapiexo.mapper;

import carroll.tbel.restapiexo.models.dto.ClientDTO;
import carroll.tbel.restapiexo.models.entity.Client;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ClientMapper {

    public ClientDTO toDTO(Client entity){

        if (entity == null)
            return null;

        return ClientDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastName(entity.getLastName())
                .reservations(
                        entity.getReservations().stream()
                                .map(this::toInnerDTO)
                                .collect(Collectors.toList())
                )
                .build();
    }

}
