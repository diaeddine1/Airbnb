package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Location;
import btking.airbnb.Services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationServices locationServices;

    @PostMapping("/add")
    public Location addLocation(@RequestBody Location location){
        Location added_location = locationServices.save(location);
        if(added_location == null){
            return null;
        }
        return added_location;
    }

    @PutMapping("update/{id}")
    public Location updateLocation(@RequestBody Location location,@PathVariable(required = true) String id){
        Location updated_location = locationServices.findById(id);
        if(updated_location == null){
            return null;
        }

        return updated_location = locationServices.update(location);
    }

    @DeleteMapping("delete/{id}")
    public void deleteLocation(@PathVariable(required = true) String id){
        Location deleted_location = locationServices.findById(id);
        if(deleted_location == null){
            System.out.println("Cannot find location with id to delete it : "+id);
        }
        locationServices.delete(deleted_location);
    }

    @GetMapping("/{id}")
    public Location getLocationById(@PathVariable String id){
        Location location = locationServices.findById(id);
        if(location == null){
            return null;
        }
        return  location;
    }

    @GetMapping("/all")
    public List<Location> getAllLocations(){
        List<Location> locations = locationServices.findAll();
        if(locations == null){
            return null;
        }
        return locations;
    }
}
