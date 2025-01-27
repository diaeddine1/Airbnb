package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReviewRepository extends MongoRepository<Review, String> {

    Optional<Review> getReviewById(String id);


}
