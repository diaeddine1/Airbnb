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
@ToString
public class Location {

    @Id
    private String id;

    private String country;
    private String city;
    private String street;

    public Location(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }
}
