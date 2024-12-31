package btking.airbnb.Repositories;

import btking.airbnb.Models.House;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends MongoRepository<House, String> {
//    List<House> findAll

    @Override
    List<House> findAll();
    House getHouseByTitle(String title);
    House getHouseById(String id);

}
