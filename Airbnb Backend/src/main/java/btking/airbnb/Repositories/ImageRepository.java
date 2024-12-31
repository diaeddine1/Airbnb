package btking.airbnb.Repositories;


import btking.airbnb.Models.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends MongoRepository<Image, String> {
    Image getImageById(String id);

}
