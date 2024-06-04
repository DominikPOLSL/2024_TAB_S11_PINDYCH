package com.example.carrent.vehiclecargiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiclecargiver")
public class VehicleCarGiverController {

    private final VehicleCarGiverService vehicleCarGiverService;

    @Autowired
    public VehicleCarGiverController(VehicleCarGiverService vehicleCarGiverService) {
        this.vehicleCarGiverService = vehicleCarGiverService;
    }

    @GetMapping
    public List<VehicleCarGiver> getAllVehicleCarGivers() {
        return vehicleCarGiverService.getAllVehicleCarGivers();
    }

    @GetMapping("/{id}")
    public Optional<VehicleCarGiver> getVehicleCarGiverById(@PathVariable int id) {
        return vehicleCarGiverService.getVehicleCarGiverById(id);
    }

    @PostMapping
    public void addVehicleCarGiver(@RequestBody VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiverService.addVehicleCarGiver(vehicleCarGiver);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicleCarGiver(@PathVariable int id) {
        vehicleCarGiverService.deleteVehicleCarGiver(id);
    }

    @PutMapping("/{id}")
    public void updateVehicleCarGiver(@PathVariable int id, @RequestBody VehicleCarGiver vehicleCarGiver) {
        vehicleCarGiver.setId(id);
        vehicleCarGiverService.updateVehicleCarGiver(vehicleCarGiver);
    }
}
