package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
    Location getLocationById(String id);

}
