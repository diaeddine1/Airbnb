package btking.airbnb.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Locations")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Location {

    @Id
    private String id;
    private String country;
    private String city;
    private String street;

    @DBRef
    private RegisteredGood registeredGood;
}
