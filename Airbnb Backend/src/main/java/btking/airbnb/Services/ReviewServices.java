package btking.airbnb.Services;

import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Comment;
import btking.airbnb.Models.Restaurant;
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


    @Autowired
    private RestaurantServices restaurantServices;

    @Override
    public Review save(Review review) {


        User user = userService.findById(review.getUser().getId());

        if (user == null) {
            throw new RuntimeException("User with ID " + review.getUser().getId() + " not found.");
        }

        Restaurant restaurant=restaurantServices.findById(review.getRegisteredGood().getId());

        System.out.println("The restaurant informations are :"+restaurant);



        if (user.getReviews()==null){
            System.out.println("The reviews are null creating a new arraylist");
            user.setReviews(new ArrayList<>());
            System.out.println("The new arraylist size is : "+user.getReviews().size());
        }
        user.getReviews().add(review);
        System.out.println("The new arraylist size is : "+user.getReviews().size());
        System.out.println(user.getReviews());


        User updated_user = userService.findById(review.getUser().getId());
        System.out.println("The new arraylist size is : "+updated_user.getReviews().size());
        commentServices.saveAll(review.getComment());
        Review added_review =  reviewRepository.save(review);
        userService.update(user);
        return added_review;
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
