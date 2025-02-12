package btking.airbnb.Services;

import btking.airbnb.DTOS.RegisteredGood_ReviewsDTO;
import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Mappers.RegisteredGood_ReviewsDTOMapper;
import btking.airbnb.Models.Restaurant;
import btking.airbnb.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServices implements Idao<Restaurant> {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RegisteredGood_ReviewsDTOMapper reviewDTOMapper;

    @Autowired
    private LocationServices locationServices;


    @Autowired
    private DishServices dishServices;

    @Override
    public Restaurant save(Restaurant restaurant) {
        locationServices.save(restaurant.getLocation());
        dishServices.saveall(restaurant.getDishes());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        return save(restaurant);
    }

    @Override
    public void delete(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    @Override
    public Restaurant findById(String id) {
        return restaurantRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Restaurant With the id [%s] not found!".formatted(id)));
    }

    public List<RegisteredGood_ReviewsDTO> getreviews(String id){
        return restaurantRepository.findById(id)
                .stream()
                .flatMap(reviewDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
