package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.exceptions.ElementAlreadyExistsException;
import carroll.tbel.restapiexo.exceptions.ElementNotFoundException;
import carroll.tbel.restapiexo.mapper.ReservationMapper;
import carroll.tbel.restapiexo.models.dto.ReservationDTO;
import carroll.tbel.restapiexo.models.entity.Client;
import carroll.tbel.restapiexo.models.entity.Reservation;
import carroll.tbel.restapiexo.models.forms.ReservationForm;
import carroll.tbel.restapiexo.models.forms.ReservationUpdateForm;
import carroll.tbel.restapiexo.repository.ClientRepository;
import carroll.tbel.restapiexo.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationMapper mapper;
    private final ReservationRepository repository;
    private final ClientRepository clientRepository;

    public ReservationServiceImpl(ReservationMapper mapper, ReservationRepository repository, ClientRepository clientRepository) {
        this.mapper = mapper;
        this.repository = repository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ReservationDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getOne(String resId) {
        return repository.findById(resId)
                .map(mapper::toDTO)
                .orElseThrow(ElementNotFoundException::new);
    }

    @Override
    public ReservationDTO insert(ReservationForm form) {

        if( repository.existsById(form.getResID()) )
            throw new ElementAlreadyExistsException();

        Reservation toInsert = mapper.formToEntity(form);
        Set<Client> client = form.getClientIds()
                .stream()
                .map(id -> clientRepository.findById(id)
                        .orElseThrow(ElementNotFoundException::new))
                .collect(Collectors.toSet());
        toInsert.setClient( client );

        toInsert = repository.save(toInsert);

        return mapper.toDTO(toInsert);
    }

    @Override
    public ReservationDTO delete(String resId) {
        Reservation toDelete = repository.findById(resId)
                .orElseThrow(ElementAlreadyExistsException::new);

        repository.delete(toDelete);

        return mapper.toDTO(toDelete);
    }

    @Override
    public ReservationDTO update(String resId, ReservationUpdateForm form) {
        Reservation toUpdate = repository.findById(resId)
                .orElseThrow(ElementNotFoundException::new);

        toUpdate.setArrivalDate(form.getArrivalDate());
        toUpdate.setCheckoutDate(form.getCheckoutDate());
        Set<Client> client = form.getClient()
                .stream()
                .map(id -> clientRepository.findById(id)
                        .orElseThrow(ElementNotFoundException::new))
                .collect(Collectors.toSet());
        toUpdate.setClient( client );

        toUpdate = repository.save(toUpdate);

        return mapper.toDTO(toUpdate);
    }
}
