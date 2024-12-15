package btking.airbnb.Services;


import btking.airbnb.Models.User;
import btking.airbnb.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found with username: " + username);
            throw new UsernameNotFoundException("User with name : " + username + "not found");
        }
        return user;

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
