package btking.airbnb.Models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;

    @DBRef
    private List<Reservation> reservations;

    @DBRef
    private List<Message> message;

    @JsonManagedReference
    @DBRef
    private List<Review> reviews;


    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
