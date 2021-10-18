package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.exceptions.ElementNotFoundException;
import carroll.tbel.restapiexo.mapper.ClientMapper;
import carroll.tbel.restapiexo.models.dto.ClientDTO;
import carroll.tbel.restapiexo.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientMapper mapper;
    private final ClientRepository repository;

    public ClientServiceImpl(ClientMapper mapper, ClientRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public ClientDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }
}
