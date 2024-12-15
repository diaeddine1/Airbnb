package btking.airbnb.SpringSecurityConfiguration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->{request.anyRequest().authenticated();}) // Secures every api with Login
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults()) // Enables 3rd party api calls (postman)
               // .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) With Every request u need to parse your session id
                .build();
    }

//    @Bean // Use this if you want to log in using these created users
//    public UserDetailsService userDetailsService() throws Exception {
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("adnane")
//                .password("test@321")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("dia")
//                .password("test@123")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        authProvider.setUserDetailsService(userDetailsService);
//        return authProvider;
//    }
}