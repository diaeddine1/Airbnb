package btking.airbnb.Repositories;


import btking.airbnb.Models.Comment;
import btking.airbnb.Models.House;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    Comment getCommentById(String id);

}
