package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Dish;
import btking.airbnb.Services.DishServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishServices dishServices;

    @PostMapping("/add")
    public Dish addDish(@RequestBody Dish dish){
        Dish added_dish = dishServices.save(dish);
        if(added_dish == null){
            return null;
        }
        return added_dish;
    }

    @PutMapping("update/{id}")
    public Dish updateDish(@RequestBody Dish dish,@PathVariable(required = true) String id){
        Dish updated_dish = dishServices.findById(id);
        if(updated_dish == null){
            return null;
        }

        return updated_dish = dishServices.update(dish);
    }

    @DeleteMapping("delete/{id}")
    public void deleteDish(@PathVariable(required = true) String id){
        Dish deleted_dish = dishServices.findById(id);
        if(deleted_dish == null){
            System.out.println("Cannot find dish with id to delete it : "+id);
        }
        dishServices.delete(deleted_dish);
    }

    @GetMapping("/{id}")
    public Dish getDishById(@PathVariable String id){
        Dish dish = dishServices.findById(id);
        if(dish == null){
            return null;
        }
        return  dish;
    }

    @GetMapping("/all")
    public List<Dish> getAllDishs(){
        List<Dish> dishs = dishServices.findAll();
        if(dishs == null){
            return null;
        }
        return dishs;
    }
}
