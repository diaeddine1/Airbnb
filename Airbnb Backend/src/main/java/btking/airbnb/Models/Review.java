package btking.airbnb.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private Double rating;
    @JsonBackReference
    @DBRef
    private User user;

    @DBRef
    private RegisteredGood registeredGood;

    @DBRef
    private List<Comment> comment;
}
