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
    public List<Reservation> getAllReservations() {
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

    @GetMapping("/printAllReservationById")
    public List<ReservationRecord> PrintAllReservation()
    {
        return reservationService.PrintAllReservation();
    }

    @GetMapping("/printAllReservationByEmployeeId/{id}")
    public List<ReservationRecord> PrintAllReservationEmployeeId(@PathVariable int id)
    {
        return reservationService.PrintAllReservationEmployeeId(id);
    }
    @GetMapping("/printAllReservationByGiverId/{id}")
    public List<ReservationRecord> PrintAllReservationGiverId(@PathVariable int id)
    {
        return reservationService.PrintAllReservationGiverId(id);
    }
    @PostMapping("/AddReservation")
    public Reservation addReservation(@RequestBody ReservationSave reservationSave) {
        return reservationService.addReservation(reservationSave);
    }
}
