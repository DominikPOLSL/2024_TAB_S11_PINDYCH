package com.example.carrent.reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    //@Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationRecord> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }

    @PutMapping("/{id}")
    public void updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setReservationId(id);
        reservationService.updateReservation(reservation);
    }
    
    // @GetMapping("/printAllReservationById")
    // public List<ReservationRecord> PrintAllReservation()
    // {
    //     return reservationService.PrintAllReservation();
    // }

    @GetMapping("/getAllReservationsByEmployeeId/{id}")
    public List<ReservationRecord> getAllReservationsByEmployeeId(@PathVariable int id) {
        return reservationService.getAllReservationsByEmployeeId(id);
    }

    @PostMapping
    public Reservation addReservation(@RequestBody ReservationSave reservationSave) {
        return reservationService.addReservation(reservationSave);
    }

    @GetMapping("/getReservationByAttribute/{data}")
    public List<Reservation> getReservationByAttribute(@PathVariable String data) {
        return reservationService.getReservationsByAttribute(data);
    }

    @GetMapping("/isReserved/{id}")
    public boolean isReserved(@PathVariable int id) {
        return reservationService.isReserved(id);
    }

    @GetMapping("/PrintAllRentsByUserId/{id}")
    public List<RentRecord> PrintAllRentsByUserId(@PathVariable int id)
    {
        return reservationService.PrintAllRentsByUserId(id);
    }

    @GetMapping("/getRentByAttribute/{data}")
    public List<RentRecord> getRentByAttribute(@PathVariable String data) {
        return reservationService.getRentByAttribute(data);
    }
}
