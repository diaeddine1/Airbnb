package btking.airbnb.Mappers;

import btking.airbnb.DTOS.HouseDTO;
import btking.airbnb.Models.House;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HouseDTOMapper implements Function<House, HouseDTO> {
    @Override
    public HouseDTO apply(House house) {
        return new HouseDTO(
                house.getId(),
                house.getTitle(),
                house.getPrice(),
                house.getDescription(),
                house.getRating(),
                house.getLocation(),
                house.getImages(),
                house.getRegisterDate()
        );
    }
}
