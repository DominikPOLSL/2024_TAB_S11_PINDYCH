package com.example.carrent.rent;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import com.example.carrent.reservation.Reservation;
import com.example.carrent.reservation.ReservationRepository;
import com.example.carrent.vehicle.Vehicle;
import com.example.carrent.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ReservationRepository reservationRepository;
    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public RentService(RentRepository rentRepository, ReservationRepository reservationRepository, VehicleRepository vehicleRepository, ModelRepository modelRepository, BrandRepository brandRepository) {
        this.rentRepository = rentRepository;
        this.reservationRepository = reservationRepository;
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
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

    public Optional<Rent> deleteRent(int id) {
        Optional<Rent> rentOpt = rentRepository.findById(id);

        rentOpt.ifPresent(rent -> {
            int reservationId = rent.getReservationId();
            rentRepository.deleteById(id);
            reservationRepository.findById(reservationId).ifPresent(reservation -> reservationRepository.deleteById(reservationId));
        });

        return rentOpt;
    }

    public void updateRent(Rent rent) {
        rentRepository.save(rent);
    }

    public List<String> createNewRent(int id) {
        Rent rent = new Rent();
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
        Vehicle vehicle = vehicleRepository.findById(reservation.getVehicleId()).orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + reservation.getVehicleId()));
        Model model = modelRepository.findById(vehicle.getModelId()).orElseThrow(() -> new IllegalArgumentException("Model not found with id: " + vehicle.getModelId()));
        Brand brand = brandRepository.findById(model.getBrandId()).orElseThrow(() -> new IllegalArgumentException("Brand not found with id: " + model.getBrandId()));

        rent.setCost(0);
        rent.setDistance(0);
        rent.setReservationId(id);
        reservation.setReserved(true);

        rentRepository.save(rent);
        reservationRepository.save(reservation);

        return Arrays.asList(reservation.getStartTime().toString(), model.getModelName(), brand.getBrandName());
    }
}
