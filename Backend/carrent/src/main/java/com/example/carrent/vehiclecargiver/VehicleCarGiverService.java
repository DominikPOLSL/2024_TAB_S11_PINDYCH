package com.example.carrent.vehiclecargiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleCarGiverService {

    private final VehicleCarGiverRepository vehicleCarGiverRepository;

    @Autowired
    public VehicleCarGiverService(VehicleCarGiverRepository vehicleCarGiverRepository) {
        this.vehicleCarGiverRepository = vehicleCarGiverRepository;
    }

    public List<VehicleCarGiver> getAllVehicleCarGivers() {
        return vehicleCarGiverRepository.findAll();
    }

    public void addVehicleCarGiver(VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverRepository.save(vehicleCarGiver);
    }

    public Optional<VehicleCarGiver> getVehicleCarGiverById(int id) {
        return vehicleCarGiverRepository.findById(id);
    }

    public void deleteVehicleCarGiver(int id) {
        vehicleCarGiverRepository.deleteById(id);
    }

    public void updateVehicleCarGiver(VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverRepository.save(vehicleCarGiver);
    }
}
