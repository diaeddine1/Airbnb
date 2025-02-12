package btking.airbnb.DTOS;

import java.util.List;

public record RegisteredGood_ReviewsDTO(

        String id,
        String goods_title,

        //User user,

        String user_id,
        String username,
        String user_email,


        //List<Comment> comments

        List<String> user_comments
        //List<Review> reviews,


) {


}
