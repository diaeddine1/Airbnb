package btking.airbnb.Repositories;

import btking.airbnb.Models.House;
import btking.airbnb.Models.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

    Restaurant getRestaurantById(String id);

}
