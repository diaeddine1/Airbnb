package btking.airbnb.Repositories;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

    Reservation getReservationById(String id);

}
