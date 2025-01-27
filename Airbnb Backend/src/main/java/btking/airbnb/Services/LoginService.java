package btking.airbnb.Services;


import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.Models.User;
import btking.airbnb.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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

        return userRepository.findByEmailAndPassword(email, password)
                .orElseThrow(()->new ResourceNotFound("Invalid email or password!"));
    }

    public String verify(User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if (authentication.isAuthenticated()) {
                return jwtService.generateJWTToken(user.getUsername());
            }
        } catch (BadCredentialsException e) {
            throw new ResourceNotFound("Invalid username or password.");
        }
        return null;
    }
}
