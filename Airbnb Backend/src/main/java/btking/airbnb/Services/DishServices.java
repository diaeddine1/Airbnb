package btking.airbnb.Services;

import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Dish;
import btking.airbnb.Repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishServices implements Idao<Dish> {

    @Autowired
    private DishRepository dishRepository;
    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish update(Dish dish) {
        return save(dish);
    }

    @Override
    public void delete(Dish dish) {
        dishRepository.delete(dish);
    }

    @Override
    public Dish findById(String id) {
        return dishRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Dish With the id [%s] not found!".formatted(id)));
    }

    @Override
    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
