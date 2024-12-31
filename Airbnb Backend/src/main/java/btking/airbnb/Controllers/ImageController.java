package btking.airbnb.Controllers;
import btking.airbnb.Models.House;
import btking.airbnb.Models.Image;
import btking.airbnb.Services.HouseServices;
import btking.airbnb.Services.ImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageServices imageServices;

    @Autowired
    private HouseServices houseServices;

    @PostMapping("/add")
    public Image addImage(@RequestBody Image image){
        Image added_image = imageServices.save(image);
        if(added_image == null){
            return null;
        }
        return added_image;
    }

    @PostMapping("/addAll")
    public List<Image> addAllImages(@RequestPart House house,@RequestPart MultipartFile[] files) throws IOException {
        return imageServices.saveall(house,files);
    }

    @PostMapping("/add/{id}")
    public Image addImagebyHouseId(@RequestBody Image image,@PathVariable String id){

        House house = houseServices.findById(id);
        if(house == null || image == null){
            System.out.println("house id is null or image is null"+"House id is null"+house);
            return null;
        }
        System.out.println("house id is "+house.getId());
        Image image1 = imageServices.save(image);
        image1.setHouse(house);

        return image1;

    }
    @PutMapping("update/{id}")
    public Image updateImage(@RequestBody Image image,@PathVariable(required = true) String id){
        Image updated_image = imageServices.getImageById(id);
        if(updated_image == null){
            return null;
        }

        return updated_image = imageServices.update(image);
    }

    @DeleteMapping("delete/{id}")
    public void deleteImage(@PathVariable(required = true) String id){
        Image deleted_image = imageServices.getImageById(id);
        if(deleted_image == null){
            System.out.println("Cannot find image with id to delete it : "+id);
        }
        imageServices.delete(deleted_image);
    }

    @GetMapping("/{id}")
    public Image getImageById(@PathVariable String id){
        Image image = imageServices.getImageById(id);
        if(image == null){
            return null;
        }
        return  image;
    }

    @GetMapping("/all")
    public List<Image> getAllImages(){
        List<Image> images = imageServices.findAll();
        if(images == null){
            return null;
        }
        return images;
    }


}
