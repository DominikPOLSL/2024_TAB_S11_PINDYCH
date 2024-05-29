package com.example.carrent.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
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
}
