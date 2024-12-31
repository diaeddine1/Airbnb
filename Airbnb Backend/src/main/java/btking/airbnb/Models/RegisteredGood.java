package btking.airbnb.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredGood {

    @Id
    private String id;
    private String title;
    private Float price;
    private String description;
    private Float rating;

    @DBRef
    private Location location;

    @JsonManagedReference
    @DBRef
    private List<Image> images;

    @DBRef
    private List<Review> reviews;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date registerDate;


}
