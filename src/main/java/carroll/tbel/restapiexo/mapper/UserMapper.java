package carroll.tbel.restapiexo.mapper;

import carroll.tbel.restapiexo.models.dto.UserDTO;
import carroll.tbel.restapiexo.models.entity.User;
import carroll.tbel.restapiexo.models.forms.UserForm;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDTO toDTO(User user){
        if (user == null)
            return null;

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .roles(user.getRoles())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .build();
    }

    public User forToEntity(UserForm form){
        if (form == null)
            return null;

        User u = new User();
        u.setUsername(form.getUsername());
        u.setPassword(form.getPassword());
        u.setRoles(form.getRoles());

        return u;
    }

}
