package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.exceptions.ElementNotFoundException;
import carroll.tbel.restapiexo.mapper.UserMapper;
import carroll.tbel.restapiexo.models.dto.UserDTO;
import carroll.tbel.restapiexo.models.entity.User;
import carroll.tbel.restapiexo.models.forms.UserForm;
import carroll.tbel.restapiexo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.mapper = mapper;
        this.encoder = encoder;
    }

    @Override
    public UserDTO insert(UserForm form) {
        if (form.getRoles().stream().anyMatch(role -> !role.equals("USER") && !role.equals("ADMIN")) )
            throw new RoleInvalidException();

        User toInsert = mapper.forToEntity(form);
        toInsert.setPassword(encoder.encode(form.getPassword() ));
        toInsert = repository.save(toInsert);

        return mapper.toDTO(toInsert);
    }

    @Override
    public UserDTO update(long id, UserForm form) {

        User toUpdate = repository.findById(id)
                .orElseThrow(ElementNotFoundException::new);

        if( form.getRoles().stream().anyMatch(role -> !role.equals("USER") && !role.equals("ADMIN")) )
            throw new RoleInvalidException();

        toUpdate.setUsername( form.getUsername() );
        toUpdate.setPassword( encoder.encode(form.getPassword()) );
        toUpdate.setRoles( form.getRoles() );

        toUpdate = repository.save( toUpdate );

        return mapper.toDTO(toUpdate);
    }

    @Override
    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow( () -> new UsernameNotFoundException("The user with the username provided does not exist") );
    }
}
