package btking.airbnb.Repositories;


import btking.airbnb.Models.Dish;
import btking.airbnb.Models.House;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DishRepository extends MongoRepository<Dish, String> {

    Optional<Dish> getDishById(String id);

}
