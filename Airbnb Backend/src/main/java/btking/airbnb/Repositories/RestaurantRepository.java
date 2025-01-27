package btking.airbnb.Repositories;

import btking.airbnb.Models.House;
import btking.airbnb.Models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Optional<Restaurant> getRestaurantById(String id);

}
