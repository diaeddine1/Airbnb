package btking.airbnb.Controllers;



import btking.airbnb.Models.User;
import btking.airbnb.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    private BCryptPasswordEncoder Bcrypt_encoder = new BCryptPasswordEncoder(12);
    @GetMapping("/get")
    public User getUser(@RequestParam String username) {
        User user =  userService.getUserByUsername(username);
        return user;

    }
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        user.setPassword(Bcrypt_encoder.encode(user.getPassword()));
        userService.register(user);
        return user;
    }

    @GetMapping("/getall")
    public List<User> getUserall() {
        List<User> users =  userService.getAllUsers();
        return users;

    }

    @GetMapping("/get/{username}")
    public User getUserv2(@PathVariable("username") String username) {
        User user =  userService.getUserByUsername(username);
        return user;

    }

    @GetMapping("/hello")
    public String Hello(){
        return "Hello";
    }

    @GetMapping("/login")
    public String Login(@RequestParam String email, @RequestParam String password) {
        UserDetails user = userService.LoginByEmailAndPassword(email,password);
        if(user == null){
            return "There was an error in the controller";
        }
        return "You logged in using " +user;

    }

}
