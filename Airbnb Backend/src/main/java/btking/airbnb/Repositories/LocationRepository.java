package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {


    Optional<Location> getLocationById(String id);

}
