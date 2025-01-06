package btking.airbnb.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    @Id
    private String id;
    private String imageName;
    private String imageType;
    private byte[] imageByte;
    @JsonBackReference
    @DBRef
    private RegisteredGood registeredGood;

    public Image(String imageName, String imageType, byte[] imageByte, RegisteredGood registeredGood) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageByte = imageByte;
        this.registeredGood = getRegisteredGood();
    }
    public Image(String imageName, String imageType, byte[] imageByte) {
        this.imageName = imageName;
        this.imageType = imageType;
        this.imageByte = imageByte;

    }
}
