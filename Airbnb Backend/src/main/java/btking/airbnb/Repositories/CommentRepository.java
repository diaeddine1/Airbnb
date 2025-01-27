package btking.airbnb.Repositories;


import btking.airbnb.Models.Comment;
import btking.airbnb.Models.House;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Optional<Comment> getCommentById(String id);

}
