package com.example.carrent.rent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carrent.reservation.ReservationRepository;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public RentService(RentRepository rentRepository, ReservationRepository reservationRepository) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }

    public void addRent(Rent rent) {

        rentRepository.save(rent);
        //reservationRepository.deleteById(rent.getReservationId()); //baza jest zla xd XD XDDDDD
    }

    public Optional<Rent> getRentById(int id) {
        return rentRepository.findById(id);
    }

    public void deleteRent(int id) {
        rentRepository.deleteById(id);
    }

    public void updateRent(Rent rent) {
        rentRepository.save(rent);
    }

    
}
