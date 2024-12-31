package btking.airbnb.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.GetMapping;

@Document(collection = "Dishes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Dish {

    @Id
    private String id;
    private String name;

    @DBRef
    private Restaurant restaurant;
}