package btking.airbnb.Models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Document(collection = "Houses")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class House extends RegisteredGood{
    private String type;

    private Integer numberOfRooms;
    private Integer numberOfFloors;
    private Integer numberOfBathrooms;
    private Integer maximumNumberOfGuests;

    private RegisteredGoodType registeredGoodType=RegisteredGoodType.HOUSE;

}
