package btking.airbnb.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant extends RegisteredGood{

    private Integer seatingCapacity;
    private RegisteredGoodType registeredGoodType=RegisteredGoodType.RESTAURANT;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private Date openTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "HH:mm")
    private Date closeTime;

    @DBRef
    private List<Dish> dishes;


}
