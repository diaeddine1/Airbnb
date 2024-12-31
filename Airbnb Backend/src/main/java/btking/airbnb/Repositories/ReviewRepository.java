package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewRepository extends MongoRepository<Review, String> {

    Review getReviewById(String id);


}
