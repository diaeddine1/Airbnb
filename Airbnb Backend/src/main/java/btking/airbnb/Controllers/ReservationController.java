package btking.airbnb.Controllers;


import btking.airbnb.Models.House;
import btking.airbnb.Models.Reservation;
import btking.airbnb.Services.ReservationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    
    @Autowired
    private ReservationServices reservationServices;

    @PostMapping("/add")
    public Reservation addReservation(@RequestBody Reservation reservation){
        Reservation added_reservation = reservationServices.save(reservation);
        if(added_reservation == null){
            return null;
        }
        return added_reservation;
    }
  
    @PutMapping("update/{id}")
    public Reservation updateReservation(@RequestBody Reservation reservation,@PathVariable(required = true) String id){
        Reservation updated_reservation = reservationServices.findById(id);
        if(updated_reservation == null){
            return null;
        }

        return updated_reservation = reservationServices.update(reservation);
    }

    @DeleteMapping("delete/{id}")
    public void deleteReservation(@PathVariable(required = true) String id){
        Reservation deleted_reservation = reservationServices.findById(id);
        if(deleted_reservation == null){
            System.out.println("Cannot find reservation with id to delete it : "+id);
        }
        reservationServices.delete(deleted_reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id){
        Reservation reservation = reservationServices.findById(id);
        if(reservation == null){
            return null;
        }
        return  reservation;
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations(){
        List<Reservation> reservations = reservationServices.findAll();
        if(reservations == null){
            return null;
        }
        return reservations;
    }
}
