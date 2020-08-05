package healthconnect.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;


    @Autowired
    public SecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, DataSource dataSource) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(
                passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers("/users/login","/", "/users/login-error", "/resources/**", "/index").permitAll()
                .antMatchers("/home").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
                .antMatchers("/doctors/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/departments/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/department/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/prescriptions/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/appointments/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/appointment/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/doctors/**").hasAnyRole("PATIENT", "ADMIN")
                .antMatchers("/about-us").hasAnyRole("PATIENT", "ADMIN")
                .and()
                .formLogin()
                .loginPage("/users/login")
                .successForwardUrl("/home")//doesnt work
                .failureForwardUrl("/users/login-error")
                .and()
                .logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");


        ////h2 magic, TODO: delete me
        //http.csrf().disable();
        //http.headers().frameOptions().disable();
    }
}
