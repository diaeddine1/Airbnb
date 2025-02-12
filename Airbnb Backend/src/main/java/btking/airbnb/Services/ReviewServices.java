package btking.airbnb.Services;

import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Comment;
import btking.airbnb.Models.Review;
import btking.airbnb.Models.User;
import btking.airbnb.Repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ReviewServices implements Idao<Review> {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentServices commentServices;



    @Override
    public Review save(Review review) {
        User user = userService.findById(review.getUser().getId());
        if (user.getReviews()==null){
            user.setReviews(new ArrayList<>());
        }
        user.getReviews().add(review);
        userService.update(user);

        commentServices.saveAll(review.getComment());

        userService.update(review.getUser());

        return reviewRepository.save(review);
    }




    @Override
    public Review update(Review review) {
        return save(review);
    }

    @Override
    public void delete(Review review) {
        reviewRepository.delete(review);
    }

    @Override
    public Review findById(String id) {
        return reviewRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Review With the id [%s] not found!".formatted(id)));
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
