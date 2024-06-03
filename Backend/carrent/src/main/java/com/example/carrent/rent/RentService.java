package com.example.carrent.rent;

import java.util.List;
import java.util.Optional;

import com.example.carrent.reservation.Reservation;
import com.example.carrent.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carrent.reservation.ReservationRepository;
@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @Autowired
    public RentService(RentRepository rentRepository, ReservationRepository reservationRepository, ReservationService reservationService) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public void addRent(Rent rent) {

        rentRepository.save(rent);
    }

    public Optional<Rent> getRentById(int id) {
        return rentRepository.findById(id);
    }
    public Integer getReservation(int rentId) {
        return rentRepository.findReservationIdByRentId(rentId);
    }

    public Optional<Rent> deleteRent(int id) {
        Optional<Rent> rentOpt = rentRepository.findById(id);

        if (!rentOpt.isPresent()) {
            return Optional.empty();
        }

        Rent rent = rentOpt.get();
        Optional<Reservation> reservationOpt = reservationRepository.findById(rent.getReservationId());

        if (reservationOpt.isPresent()) {
            reservationRepository.deleteById(rent.getReservationId());
        }

        rentRepository.deleteById(id);
        return rentOpt;
    }


    public void updateRent(Rent rent) {
        rentRepository.save(rent);
    }
    
}
