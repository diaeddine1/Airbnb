package btking.airbnb.Controllers;



import btking.airbnb.Models.User;
import btking.airbnb.Services.LoginService;
import btking.airbnb.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    private BCryptPasswordEncoder Bcrypt_encoder = new BCryptPasswordEncoder(12);

    @GetMapping("/get")
    public User getUser(@RequestParam String username) {
        User user =  userService.getUserByUsername(username);
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

//    @PostMapping("/login")
//    public String Login(@RequestBody User user) {
//        User login = loginService.LoginByEmailAndPassword(user.getEmail(),Bcrypt_encoder.encode(user.getPassword()));
//        if(login== null){
//            System.out.println("No user registered with this email or password : " + user.getEmail());
//            return "No user registered with this email or password : " + user.getEmail();
//        }
//        return "You logged in using " +user.getEmail();
//
//    }
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return loginService.verify(user);
    }
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        user.setPassword(Bcrypt_encoder.encode(user.getPassword()));
        userService.register(user);
        return user;
    }

}
