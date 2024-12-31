package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {

    Message getMessageById(String id);

}
