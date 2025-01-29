package btking.airbnb.Services;

import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Comment;
import btking.airbnb.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServices implements Idao<Comment> {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<List<Comment>> saveAll(List<Comment> comments){
        if(comments==null){
            return Optional.empty();
        }
        int size = comments.size();
        switch (size){
            case 0:
                return Optional.empty();
            case 1:
                save(comments.getFirst());
                break;
            default:
                for (Comment comment : comments) {
                    commentRepository.save(comment);
                }
                break;

        }
        return Optional.of(comments);

    }

    @Override
    public Comment update(Comment comment) {
        return save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Comment With the id [%s] not found!".formatted(id)));
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
