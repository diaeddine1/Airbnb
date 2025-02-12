package btking.airbnb.Mappers;

import btking.airbnb.DTOS.RegisteredGood_ReviewsDTO;
import btking.airbnb.Models.Comment;
import btking.airbnb.Models.RegisteredGood;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Stream;

@Service
public class RegisteredGood_ReviewsDTOMapper implements Function<RegisteredGood, Stream<RegisteredGood_ReviewsDTO>> {

    @Override
    public Stream<RegisteredGood_ReviewsDTO> apply(RegisteredGood registeredGood) {

        return registeredGood.getReviews()
                .stream()
                .map(review -> new RegisteredGood_ReviewsDTO(
                        registeredGood.getId(),  // RegisteredGood ID
                        registeredGood.getTitle(),  // RegisteredGood Title
                        review.getUser().getId(),  // User ID
                        review.getUser().getUsername(),  // Username
                        review.getUser().getEmail(),  // Email
                        review.getComment()
                                .stream()
                                .map(Comment::getComment) // Extract comment text
                                .toList()
                ));
    }


}
