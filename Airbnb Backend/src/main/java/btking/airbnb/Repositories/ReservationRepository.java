package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    Optional<Reservation> getReservationById(String id);

}
