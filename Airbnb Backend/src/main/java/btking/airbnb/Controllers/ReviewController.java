package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Review;
import btking.airbnb.Services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewServices reviewServices;

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review){
        Review added_review = reviewServices.save(review);
        if(added_review == null){
            return null;
        }
        return added_review;
    }

    @PutMapping("update/{id}")
    public Review updateReview(@RequestBody Review review,@PathVariable(required = true) String id){
        Review updated_review = reviewServices.findById(id);
        if(updated_review == null){
            return null;
        }

        return updated_review = reviewServices.update(review);
    }

    @DeleteMapping("delete/{id}")
    public void deleteReview(@PathVariable(required = true) String id){
        Review deleted_review = reviewServices.findById(id);
        if(deleted_review == null){
            System.out.println("Cannot find review with id to delete it : "+id);
        }
        reviewServices.delete(deleted_review);
    }

    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable String id){
        return  reviewServices.findById(id);
    }

    @GetMapping("/all")
    public List<Review> getAllReviews(){
        List<Review> reviews = reviewServices.findAll();
        if(reviews == null){
            return null;
        }
        return reviews;
    }
}
