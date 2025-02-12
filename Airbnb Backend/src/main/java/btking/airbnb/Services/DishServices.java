package btking.airbnb.Services;

import btking.airbnb.Exception.ResourceNotFound;
import btking.airbnb.IDao.Idao;

import btking.airbnb.Models.Dish;
import btking.airbnb.Repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class DishServices implements Idao<Dish> {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish save(Dish dish) {
        return dishRepository.save(dish);
    }
    
    public Optional<List<Dish>> saveall(List<Dish> dishes){
        if(dishes==null){
            return Optional.empty();
        }
        int size = dishes.size();
        switch (size){
            case 0:
                return Optional.empty();
            case 1:
                save(dishes.getFirst());
                break;
            default:
                for (Dish dish : dishes) {
                    dishRepository.save(dish);
                }
                break;

        }
        return Optional.of(dishes);
        
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
