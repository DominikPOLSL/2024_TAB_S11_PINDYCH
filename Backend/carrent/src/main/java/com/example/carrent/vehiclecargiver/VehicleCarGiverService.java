package com.example.carrent.vehiclecargiver;

/**
 * Service for managing vehicle car givers.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleCarGiverService {

    private final VehicleCarGiverRepository vehicleCarGiverRepository;

    /**
     * Constructor for VehicleCarGiverService.
     * @param vehicleCarGiverRepository The repository for vehicle car givers.
     */
    @Autowired
    public VehicleCarGiverService(VehicleCarGiverRepository vehicleCarGiverRepository) {
        this.vehicleCarGiverRepository = vehicleCarGiverRepository;
    }

    /**
     * Retrieves all vehicle car givers.
     * @return A list of all vehicle car givers.
     */
    public List<VehicleCarGiver> getAllVehicleCarGivers() {
        return vehicleCarGiverRepository.findAll();
    }

    /**
     * Adds a new vehicle car giver.
     * @param vehicleCarGiver The vehicle car giver to add.
     */
    public void addVehicleCarGiver(VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverRepository.save(vehicleCarGiver);
    }

    /**
     * Deletes a vehicle car giver by ID.
     * @param id The ID of the vehicle car giver to delete.
     */
    public Optional<VehicleCarGiver> getVehicleCarGiverById(int id) {
        return vehicleCarGiverRepository.findById(id);
    }
    /**
     * Deletes a vehicle car giver by ID.
     * @param id The ID of the vehicle car giver to delete.
     */
    public void deleteVehicleCarGiver(int id) {
        vehicleCarGiverRepository.deleteById(id);
    }
    /**
     * Updates a vehicle car giver.
     * @param vehicleCarGiver The updated vehicle car giver information.
     */
    public void updateVehicleCarGiver(VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverRepository.save(vehicleCarGiver);
    }
}
