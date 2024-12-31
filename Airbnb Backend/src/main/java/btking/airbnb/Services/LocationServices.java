package btking.airbnb.Services;

import btking.airbnb.IDao.Idao;
import btking.airbnb.Models.Location;
import btking.airbnb.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LocationServices implements Idao<Location> {

    @Autowired
    private LocationRepository locationRepository;
    @Override
    public Location save(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location update(Location location) {
        return save(location);
    }

    @Override
    public void delete(Location location) {
        locationRepository.delete(location);
    }

    @Override
    public Location findById(String id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
