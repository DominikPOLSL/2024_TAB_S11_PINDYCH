package com.example.carrent.vehicle;

import java.util.List;
import java.util.Optional;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.brand.BrandService;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.carrent.model.ModelService;

@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ModelService modelService;

    public VehicleController(VehicleService vehicleService, ModelService modelService, BrandService brandService) {
        this.vehicleService = vehicleService;
        this.modelService = modelService;
    }

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    @PostMapping()
    public ResponseEntity<?> addNewVehicle(@RequestBody Vehicle vehicle) {
        int modelId = vehicle.getModelId();

        if (!modelService.existById(modelId)) {

        }

        Vehicle createdVehicle = vehicleService.addNewVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle updatedVehicle) {

        Vehicle existingVehicle = vehicleService.findById(id);
        if (existingVehicle == null) {
            return ResponseEntity.notFound().build();
        }

        existingVehicle.setEquipment(updatedVehicle.getEquipment());
        existingVehicle.setVersion(updatedVehicle.getVersion());
        existingVehicle.setPurpose(updatedVehicle.getPurpose());
        existingVehicle.setTotalTime(updatedVehicle.getTotalTime());
        existingVehicle.setTotalDistance(updatedVehicle.getTotalDistance());
        existingVehicle.setFuel(updatedVehicle.getFuel());
        existingVehicle.setYearOfProduction(updatedVehicle.getYearOfProduction());
        existingVehicle.setModelId(updatedVehicle.getModelId());
        existingVehicle.setPower(updatedVehicle.getPower());

        int modelId = updatedVehicle.getModelId();
        if (!modelService.existById(modelId)) {
            return ResponseEntity.badRequest().body("Model does not exist.");
        }

        Vehicle savedVehicle = vehicleService.save(existingVehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") int id) {
        Vehicle vehicle = vehicleService.findById(id);
        if (vehicle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{id}")
    public Optional<Vehicle> deleteVehicle(@PathVariable("id") int id) {
        if (!vehicleService.existById(id)) {
            //return ResponseEntity.notFound().build();
        }
        return vehicleService.deleteVehicleById(id);
    }

    @GetMapping("/printVehicle/{id}")
    public VehiclePrint PrintVehicle(@PathVariable("id") int id)
    {
        return vehicleService.PrintVehicle(id);
    }

    @GetMapping("/printVehicle")
    public List<VehiclePrint> PrintVehicle()
    {
        return vehicleService.printAllVehicles();
    }
}
