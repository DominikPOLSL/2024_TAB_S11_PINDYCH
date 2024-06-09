package com.example.carrent.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for managing reservations.
 */
@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * Constructor for ReservationController.
     * 
     * @param reservationService The ReservationService to be injected.
     */
    // @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    /**
     * Endpoint to retrieve all reservations.
     * 
     * @return A list of ReservationRecord objects representing all reservations.
     */
    @GetMapping
    public List<ReservationRecord> getAllReservations() {
        return reservationService.getAllReservations();
    }

    /**
     * Endpoint to retrieve a reservation by ID.
     * 
     * @param id The ID of the reservation to retrieve.
     * @return An Optional containing the Reservation object if found, empty
     *         otherwise.
     */
    @GetMapping("/{id}")
    public Optional<Reservation> getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    /**
     * Endpoint to delete a reservation by ID.
     * 
     * @param id The ID of the reservation to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }

    /**
     * Endpoint to update a reservation.
     * 
     * @param id          The ID of the reservation to update.
     * @param reservation The updated Reservation object.
     */
    @PutMapping("/{id}")
    public void updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        reservation.setReservationId(id);
        reservationService.updateReservation(reservation);
    }

    // Omitted commented-out method

    /**
     * Endpoint to retrieve all reservations by employee ID.
     * 
     * @param id The ID of the employee.
     * @return A list of ReservationRecord objects representing reservations made by
     *         the employee.
     */
    @GetMapping("/getAllReservationsByEmployeeId/{id}")
    public List<ReservationRecord> getAllReservationsByEmployeeId(@PathVariable int id) {
        return reservationService.getAllReservationsByEmployeeId(id);
    }

    /**
     * Endpoint to add a new reservation.
     * 
     * @param reservationSave The ReservationSave object containing information for
     *                        the new reservation.
     * @return The added Reservation object.
     */
    @PostMapping
    public Reservation addReservation(@RequestBody ReservationSave reservationSave) {
        return reservationService.addReservation(reservationSave);
    }

    /**
     * Endpoint to retrieve reservations by attribute.
     * 
     * @param data The attribute data to search for.
     * @return A list of ReservationRecord objects matching the search attribute.
     */
    @GetMapping("/getReservationByAttribute/{data}")
    public List<ReservationRecord> getReservationByAttribute(@PathVariable String data) {
        return reservationService.getReservationsByAttribute(data);
    }

    // Omitted methods for isReserved, PrintAllRentsByUserId, and getRentByAttribute
}
