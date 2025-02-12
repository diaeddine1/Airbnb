package btking.airbnb.Controllers;


import btking.airbnb.DTOS.RegisteredGood_ReviewsDTO;
import btking.airbnb.Models.Restaurant;
import btking.airbnb.Services.RestaurantServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantServices restaurantServices;



    @PostMapping("/add")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant){

        Restaurant added_restaurant = restaurantServices.save(restaurant);
        if(added_restaurant == null){
            return null;
        }
        return added_restaurant;
    }

    @PutMapping("update/{id}")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant,@PathVariable(required = true) String id){
        Restaurant updated_restaurant = restaurantServices.findById(id);
        if(updated_restaurant == null){
            return null;
        }

        return updated_restaurant = restaurantServices.update(restaurant);
    }

    @DeleteMapping("delete/{id}")
    public void deleteRestaurant(@PathVariable(required = true) String id){
        Restaurant deleted_restaurant = restaurantServices.findById(id);
        if(deleted_restaurant == null){
            System.out.println("Cannot find restaurant with id to delete it : "+id);
        }
        restaurantServices.delete(deleted_restaurant);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable String id){
        Restaurant restaurant = restaurantServices.findById(id);
        if(restaurant == null){
            return null;
        }
        return  restaurant;
    }

    @GetMapping("/all")
    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = restaurantServices.findAll();
        if(restaurants == null){
            return null;
        }
        return restaurants;
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<RegisteredGood_ReviewsDTO>> getreviews(@PathVariable String id){
        return ResponseEntity.ok(restaurantServices.getreviews(id));

    }
}
