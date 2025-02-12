package btking.airbnb.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "Reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {

    @Id
    private String id;

    private Integer rating;

    @DBRef
    private User user;

    @DocumentReference(lazy = true)  // Properly references a polymorphic class
    private RegisteredGood registeredGood;

    @DBRef
    private List<Comment> comment;
}
