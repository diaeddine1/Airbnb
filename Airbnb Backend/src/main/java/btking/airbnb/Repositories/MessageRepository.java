package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MessageRepository extends MongoRepository<Message, String> {

    Optional<Message> getMessageById(String id);

}
