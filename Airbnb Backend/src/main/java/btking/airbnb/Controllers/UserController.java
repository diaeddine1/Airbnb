package btking.airbnb.Controllers;



import btking.airbnb.Models.Review;
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

    @GetMapping("/all")
    public List<User> getUserall() {
        return userService.findAll();


    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id){
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserv2(@PathVariable("username") String username) {
        User user =  userService.getUserByUsername(username);
        return user;

    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        return loginService.verify(user);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(required = true) String id){
        User updated_user=userService.findById(id);
       // updated_user.setReviews(user.getReviews());
        if(updated_user==null){
            return null;
        }
        return userService.update(updated_user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(required = true) String id){
        User deleted_user = userService.findById(id);
        if(deleted_user == null){
            System.out.println("Cannot find review with id to delete it : "+id);
        }
        userService.delete(deleted_user);
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


    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        user.setPassword(Bcrypt_encoder.encode(user.getPassword()));
        userService.save(user);
        return user;
    }

}
