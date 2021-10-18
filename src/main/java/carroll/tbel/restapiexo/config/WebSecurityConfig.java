package carroll.tbel.restapiexo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;

    public WebSecurityConfig(UserDetailsService service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.httpBasic();

        http.authorizeRequests()
                //for reservations
                .antMatchers(HttpMethod.GET, "/reservation/**").hasAuthority("USER")
                .antMatchers("/reservation/**").hasAuthority("ADMIN")
                //for clients
                .antMatchers(HttpMethod.GET, "/client/**").hasAuthority("USER")
                //for users
                .antMatchers("/user/**").hasAuthority("ADMIN")
                //for everything else
                .anyRequest().permitAll();

        //for H2
        http.headers()
                .frameOptions()
                .disable();
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

}
