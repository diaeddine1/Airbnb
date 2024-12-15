package btking.airbnb.Controllers;



import btking.airbnb.Models.User;
import btking.airbnb.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public User getUser(@RequestParam String username) {
        User user =  userService.getUserByUsername(username);
        return user;

    }
    @PostMapping("/")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getEmail());
        userService.save(user);
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

}
