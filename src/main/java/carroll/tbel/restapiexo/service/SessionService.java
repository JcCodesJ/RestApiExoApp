package carroll.tbel.restapiexo.service;

import carroll.tbel.restapiexo.models.entity.User;
import carroll.tbel.restapiexo.models.forms.LoginForm;
import carroll.tbel.restapiexo.utils.JwtProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final AuthenticationManager auth;
    private final JwtProvider jwtProvider;



    public SessionService(AuthenticationManager auth, JwtProvider jwtProvider) {
        this.auth = auth;
        this.jwtProvider = jwtProvider;
    }

    public String login(LoginForm form){

        User user = uRep.findByUsername(form.getUsername())
                .orElseThrow( () -> new UsernameNotFoundException("The username was not found") );

        //create the authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        //test the authentication
        auth.authenticate(authentication);
        //if ok: create and send the token
        return jwtProvider.createToken(user.getUsername(), user.getRoles());
        //if not okay: deny access and send an error


    }

}
