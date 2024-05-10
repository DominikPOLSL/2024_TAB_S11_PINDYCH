package com.example.carrent.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean existById(int id) {
        return vehicleRepository.existsById(id);
    }

    public void deleteVehicleById(int id) {
        vehicleRepository.deleteById(id);
    }

    public Vehicle findById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle save(Vehicle existingVehicle) {
        return vehicleRepository.save(existingVehicle);
    }
}
