package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.models.dto.ClientDTO;

public interface ClientService {

    ClientDTO getOne(Long id);

}
