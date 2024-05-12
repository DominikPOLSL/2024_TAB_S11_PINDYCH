package com.example.carrent.vehicle;

import java.util.List;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.brand.BrandService;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.carrent.model.ModelService;

@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {


    private VehicleRepository vehicleRepository;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;


    private final VehicleService vehicleService;
    private final ModelService modelService;
    private final BrandService brandService;

    public VehicleController(VehicleService vehicleService, ModelService modelService, BrandService brandService) {
        this.vehicleService = vehicleService;
        this.modelService = modelService;
        this.brandService = brandService;
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

    @GetMapping("/printVehicle/{id}")
    @ResponseBody
    public String printVehicle(@PathVariable("id") int id) {
        return vehicleService.printVehicle(id);
    }
}
