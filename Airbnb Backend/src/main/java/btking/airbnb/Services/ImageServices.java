package btking.airbnb.Services;


import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.House;
import btking.airbnb.Models.Image;
import btking.airbnb.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service

public class ImageServices implements Idao<Image> {

    @Autowired
    private ImageRepository imageRepository;


    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image save(Image image) {

        return imageRepository.save(image);
    }

    public List<Image> saveall(@RequestPart House house, @RequestPart MultipartFile[] files) throws IOException {
        List<Image> saved_Images = new ArrayList<>();
        for (MultipartFile mf : files) {
            Image saved_Image = Image.builder()
                    .imageType(mf.getContentType())
                    .imageName(mf.getOriginalFilename())
                    .imageByte(mf.getBytes())
                    .registeredGood(house)
                    .build();
            if(saved_Image!=null){
                imageRepository.save(saved_Image);
                saved_Images.add(saved_Image);
            }
        }
       return saved_Images;
    }

//    public List<Image> addImages(House house, MultipartFile[] files) throws IOException {
//        List<Image> savedImages = new ArrayList<>();
//        for (MultipartFile file : files) {
//            // Create a new Image object for each file
//            Image image = new Image();
//            image.setImageName(file.getOriginalFilename());
//            image.setImageType(file.getContentType());
//            image.setImages(file.getBytes());
//            image.setHouse(house); // Associate the image with the house
//
//            // Save the image
//            Image savedImage = imageRepository.save(image);
//            savedImages.add(savedImage);
//        }
//        return savedImages;
//    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image);
    }

    @Override
    public Image findById(String id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image update(Image image) {
        return imageRepository.save(image);
    }



    public Image getImageById(String id) {

        return imageRepository.getImageById(id)
                .orElseThrow(()->new ResourceNotFound("Image With the id [%s] not found!".formatted(id)));
    }






}
