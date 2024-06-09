/**
 * @file VehicleCarGiverController.java
 * @brief This file contains the REST controller for managing VehicleCarGiver entities.
 */

package com.example.carrent.vehiclecargiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @brief REST controller for VehicleCarGiver entity.
 */
@RestController
@RequestMapping("/api/vehiclecargiver")
public class VehicleCarGiverController {

    /**
     * @brief Service for VehicleCarGiver operations.
     */
    private final VehicleCarGiverService vehicleCarGiverService;

    /**
     * @brief Constructor with dependency injection.
     * @param vehicleCarGiverService Service for VehicleCarGiver operations.
     */
    @Autowired
    public VehicleCarGiverController(VehicleCarGiverService vehicleCarGiverService) {
        this.vehicleCarGiverService = vehicleCarGiverService;
    }

    /**
     * @brief Gets all VehicleCarGivers.
     * @return List of all VehicleCarGivers.
     */
    @GetMapping
    public List<VehicleCarGiver> getAllVehicleCarGivers() {
        return vehicleCarGiverService.getAllVehicleCarGivers();
    }

    /**
     * @brief Gets a VehicleCarGiver by ID.
     * @param id ID of the VehicleCarGiver.
     * @return Optional containing the VehicleCarGiver if found, empty otherwise.
     */
    @GetMapping("/{id}")
    public Optional<VehicleCarGiver> getVehicleCarGiverById(@PathVariable int id) {
        return vehicleCarGiverService.getVehicleCarGiverById(id);
    }

    /**
     * @brief Adds a new VehicleCarGiver.
     * @param vehicleCarGiver VehicleCarGiver entity to add.
     */
    @PostMapping
    public void addVehicleCarGiver(@RequestBody VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverService.addVehicleCarGiver(vehicleCarGiver);
    }

    /**
     * @brief Deletes a VehicleCarGiver by ID.
     * @param id ID of the VehicleCarGiver to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteVehicleCarGiver(@PathVariable int id) {
        vehicleCarGiverService.deleteVehicleCarGiver(id);
    }

    /**
     * @brief Updates a VehicleCarGiver.
     * @param id ID of the VehicleCarGiver to update.
     * @param vehicleCarGiver Updated VehicleCarGiver entity.
     */
    @PutMapping("/{id}")
    public void updateVehicleCarGiver(@PathVariable int id, @RequestBody VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiver.setId(id);
        vehicleCarGiverService.updateVehicleCarGiver(vehicleCarGiver);
    }
}
