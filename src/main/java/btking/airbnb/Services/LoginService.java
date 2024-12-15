package btking.airbnb.Services;


import btking.airbnb.Models.User;
import btking.airbnb.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User LoginByEmailAndPassword(String email, String password) {
        User userDetails = userRepository.findByEmailAndPassword(email,password);
        if (userDetails == null) {
            return null;
        }
        return userDetails;
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateJWTToken(user.getUsername());
            //return "You logged in using " +user.getUsername();
        }
        System.out.println("No user registered with this email or password : " + user.getUsername()+user.getPassword());
        return "No user registered with this email or password : " + user.getUsername()+user.getPassword();

    }
}
