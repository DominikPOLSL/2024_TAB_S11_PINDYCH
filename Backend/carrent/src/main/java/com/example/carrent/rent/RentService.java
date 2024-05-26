package com.example.carrent.rent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private final RentRepository rentRepository;

    @Autowired
    public RentService(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
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

    public void deleteRent(int id) {
        rentRepository.deleteById(id);
    }

    public void updateRent(Rent rent) {
        rentRepository.save(rent);
    }
}
