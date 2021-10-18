package carroll.tbel.restapiexo.models.forms;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class LoginForm {

    private String username;

    private String password;

}
