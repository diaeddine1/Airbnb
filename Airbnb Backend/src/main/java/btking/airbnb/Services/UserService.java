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

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails LoginByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(UserPrincipal::new)
                .orElseThrow(()->new ResourceNotFound("Invalid email or password!"));


    }


    public User register(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFound("UserService With the username [%s] not found!".formatted(username)));


    }

    public List<User> getAllUsers() throws UsernameNotFoundException {
        List<User> users = userRepository.findAll();
        if (users == null) {
            System.out.println("Users not found");
            throw new UsernameNotFoundException("Users not found");
        }
        return users;
    }
}
