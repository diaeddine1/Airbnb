package btking.airbnb.Repositories;


import btking.airbnb.Models.Dish;
import btking.airbnb.Models.House;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DishRepository extends MongoRepository<Dish, String> {

    Dish getDishById(String id);

}
