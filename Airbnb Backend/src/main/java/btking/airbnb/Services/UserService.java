package btking.airbnb.Services;


import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Review;
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
public class UserService implements Idao<User> {

    @Autowired
    private UserRepository userRepository;

    public UserDetails LoginByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(UserPrincipal::new)
                .orElseThrow(()->new ResourceNotFound("Invalid email or password!"));


    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }



    @Override
    public void delete(User user) {
        userRepository.delete(user);

    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFound("User With the id [%s] not found!".formatted(id)));
    }

    @Override
    public List<User> findAll() throws UsernameNotFoundException{
        List<User> users = userRepository.findAll();
        if (users == null) {
            System.out.println("Users not found");
            throw new UsernameNotFoundException("Users not found");
        }
        return users;

    }


    public User getUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(()-> new ResourceNotFound("UserService With the username [%s] not found!".formatted(username)));


    }

}
