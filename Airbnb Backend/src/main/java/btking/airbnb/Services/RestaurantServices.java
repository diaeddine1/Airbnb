package btking.airbnb.Services;

import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Restaurant;
import btking.airbnb.Repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantServices implements Idao<Restaurant> {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Override
    public Restaurant save(Restaurant restaurant) {
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
        return restaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }
}
