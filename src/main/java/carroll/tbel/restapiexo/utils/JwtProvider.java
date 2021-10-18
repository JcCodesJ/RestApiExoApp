package carroll.tbel.restapiexo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    public String createToken(String username, List<String> roles){

        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 86_400_000) )
                .withClaim("roles", roles)
                .sign(Algorithm.HMAC512("p_r2jMh9NE=Zr@vD") );

        return "Bearer " + token;

    }

}
