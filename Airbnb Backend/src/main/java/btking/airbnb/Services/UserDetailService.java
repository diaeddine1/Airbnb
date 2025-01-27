package btking.airbnb.Services;


import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.Models.User;
import btking.airbnb.Models.UserPrincipal;
import btking.airbnb.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(UserPrincipal::new)
                .orElseThrow(()-> new ResourceNotFound("UserDetails With the username [%s] not found!".formatted(username)));


    }
}
