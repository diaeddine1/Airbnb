package btking.airbnb.Controllers;


import btking.airbnb.DTOS.HouseDTO;
import btking.airbnb.Models.House;
import btking.airbnb.Models.Image;
import btking.airbnb.Services.HouseServices;
import btking.airbnb.Services.ImageServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {


    @Autowired
    private HouseServices houseServices;

    @Autowired
    private ImageServices imageServices;



    @PostMapping("/add")
    public ResponseEntity<?> addHouse(@RequestPart("house") String houseJson,@RequestPart("files") MultipartFile[] files) throws IOException {
        House house = new ObjectMapper().readValue(houseJson, House.class);
        System.out.println("house :" + house);
        House saved_house=houseServices.addHouse(house);


        List<Image> images =  imageServices.saveall(house,files);
        saved_house.setImages(images);

        houseServices.update(saved_house);
        return ResponseEntity.ok(saved_house);
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> addHouse(@RequestPart House house,@RequestPart MultipartFile[] files) throws IOException {
//        System.out.println(house);
//        houseServices.addHouse(house);
//        List<Image> images =  imageServices.saveall(house,files);
//        house.setImages(images);
//        houseServices.update(house);
//        return ResponseEntity.ok(house);
//    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<House> updateHouse(@RequestBody House house,@PathVariable String id) throws IOException {
        House updated_house = houseServices.findById(id);
        if (updated_house != null) {
            return ResponseEntity.notFound().build();
        }
        houseServices.update(house);
        return ResponseEntity.ok(house);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteHouse(@RequestParam String id) throws IOException {
        House deleted_house = houseServices.findById(id);
        if (deleted_house != null) {
            System.out.println("Cannot find a house with id to delete it : "+id);
        }
        houseServices.delete(deleted_house);
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<House> getHouse(@PathVariable String title) {
        House house = houseServices.getHouseByTitle(title);
        if(house==null){
            return
                    ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(house);
    }
    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable String id) {
        House house = houseServices.findById(id);
        if(house==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(house);
    }

    @GetMapping("/all")
    public ResponseEntity<List<House>> findAllHouses(){
        List<House> houses = houseServices.findAll();
        if(houses==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(houses);
    }
//    @GetMapping("/getall")
//    public ResponseEntity<List<HouseDTO>> getAllHouses() {
//        List<HouseDTO> houseDTOS = houseServices.getAll();
//        return ResponseEntity.ok(houseDTOS);
//    }
}
