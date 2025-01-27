package btking.airbnb.Services;
import btking.airbnb.DTOS.HouseDTO;
import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Mappers.HouseDTOMapper;
import btking.airbnb.Models.House;
import btking.airbnb.Models.Image;
import btking.airbnb.Repositories.HouseRepository;
import btking.airbnb.Repositories.ImageRepository;
import btking.airbnb.Repositories.LocationRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service

public class HouseServices implements Idao<House> {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private HouseDTOMapper houseDTOMapper;

    @Autowired
    private LocationRepository locationRepository;
//    @Override
//    public List<House> findAll() {
//        return houseRepository.findAll();
//    }

    @Override
    public List<House> findAll() {
        return houseRepository.findAll();
    }


    public List<HouseDTO> getAll() {
        return houseRepository.findAll()
                .stream()
                .map(houseDTOMapper)
                .collect(Collectors.toList());



    }

    @Override
    public House save(House house) {
        return houseRepository.save(house);
    }

    @Override
    public void delete(House house) {
        houseRepository.delete(house);
    }

    @Override
    public House findById(String id) {

        return houseRepository.getHouseById(id).orElseThrow(()-> new ResourceNotFound("House With the id [%s] not found!".formatted(id)));
    }

    @Override
    public House update(House house) {
        return houseRepository.save(house);
    }

    public House addHouse(House house){
//        if (house.getImages() == null) {
//            house.setImages(new ArrayList<>());
//        }
//        for (MultipartFile file : files) {
//            // Create a new Image object from each file
//            Image image = new Image();
//            image.setImageName(file.getOriginalFilename());
//            image.setImageType(file.getContentType());
//            image.setImages(file.getBytes());
//            house.getImages().add(image);
//        }

        // Save the house
        locationRepository.save(house.getLocation());
        return houseRepository.save(house);
    }




    public House getHouseByTitle(String title) {
        return houseRepository.getHouseByTitle(title);
    }

}
