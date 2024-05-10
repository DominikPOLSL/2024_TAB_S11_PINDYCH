package com.example.carrent.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.model.ModelService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ModelService modelService;

    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();

    }

    @PostMapping()
    public ResponseEntity<?> addNewVehicle(@RequestBody Vehicle vehicle) {
        int modelId = vehicle.getModelId();
        if (!modelService.existById(modelId)) {
            return ResponseEntity.badRequest().body("Model does not exist.");
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
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") int id) {
        if (!vehicleService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehicleService.deleteVehicleById(id);
        return ResponseEntity.ok().build();
    }

}
