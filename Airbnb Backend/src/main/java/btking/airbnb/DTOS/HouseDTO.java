package btking.airbnb.DTOS;

import btking.airbnb.Models.Image;
import btking.airbnb.Models.Location;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

public record HouseDTO(

         String id,
         String title,
         Float price,
         String description,
         Float rating,
         Location location,
         List<Image>images,
         @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
         Date registerDate

) {
}
