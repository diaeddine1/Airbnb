package btking.airbnb.Repositories;

import btking.airbnb.Models.User;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {


    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndPassword(String email, String password);

}
