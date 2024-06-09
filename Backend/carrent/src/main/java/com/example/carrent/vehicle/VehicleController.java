package com.example.carrent.vehicle;

import java.util.List;
import java.util.Optional;

import com.example.carrent.brand.Brand;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for handling Vehicle-related requests.
 */
@RestController
@RequestMapping(path = "api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final ModelService modelService;

    /**
     * Constructs a new VehicleController.
     *
     * @param vehicleService the vehicle service
     * @param modelService the model service
     */
    public VehicleController(VehicleService vehicleService, ModelService modelService) {
        this.vehicleService = vehicleService;
        this.modelService = modelService;
    }

    /**
     * Retrieves a list of all vehicles.
     *
     * @return a list of vehicles
     */
    @GetMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }

    /**
     * Adds a new vehicle.
     *
     * @param vehicle the vehicle to add
     * @return a response entity containing the created vehicle or an error message
     */
    @PostMapping
    public ResponseEntity<?> addNewVehicle(@RequestBody Vehicle vehicle) {
        int modelId = vehicle.getModelId();

        if (!modelService.existById(modelId)) {
            return ResponseEntity.badRequest().body("Model does not exist.");
        }

        Vehicle createdVehicle = vehicleService.addNewVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicle);
    }

    /**
     * Updates an existing vehicle.
     *
     * @param id the id of the vehicle to update
     * @param updatedVehicle the updated vehicle details
     * @return a response entity containing the updated vehicle or an error message
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicleOptional = vehicleService.findById(id);
        if (!existingVehicleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Vehicle existingVehicle = existingVehicleOptional.get();
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

    /**
     * Retrieves a vehicle by its id.
     *
     * @param id the id of the vehicle to retrieve
     * @return a response entity containing the vehicle or a not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable("id") int id) {
        Optional<Vehicle> vehicleOptional = vehicleService.findById(id);
        if (!vehicleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicleOptional.get());
    }

    /**
     * Deletes a vehicle by its id.
     *
     * @param id the id of the vehicle to delete
     * @return a response entity containing the deleted vehicle or a not found status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") int id) {
        if (!vehicleService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Optional<Vehicle> deletedVehicle = vehicleService.deleteVehicleById(id);
        return deletedVehicle.isPresent() ? ResponseEntity.ok(deletedVehicle.get()) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Prints the details of a vehicle by its id.
     *
     * @param id the id of the vehicle to print
     * @return a response entity containing the printable vehicle details or a not found status
     */
    @GetMapping("/printVehicle/{id}")
    public ResponseEntity<?> printVehicle(@PathVariable("id") int id) {
        VehiclePrint vehiclePrint = vehicleService.printVehicle(id);
        if (vehiclePrint == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiclePrint);
    }

    /**
     * Prints the details of all vehicles.
     *
     * @return a list of printable vehicle details
     */
    @GetMapping("/printVehicle")
    public List<VehiclePrint> printAllVehicles() {
        return vehicleService.printAllVehicles();
    }

    /**
     * Prints the list of available models.
     *
     * @return a list of models
     */
    @GetMapping("/printAvailableModels")
    public List<Model> printAvailableModels() {
        return vehicleService.printAvailableModels();
    }

    /**
     * Prints the list of available brands.
     *
     * @return a list of brands
     */
    @GetMapping("/printAvailableBrands")
    public List<Brand> printAvailableBrands() {
        return vehicleService.printAvailableBrands();
    }

    /**
     * Prints the list of available models for a given brand id.
     *
     * @param id the id of the brand
     * @return a list of models for the given brand id
     */
    @GetMapping("/printAvailableModelsByBrandId/{id}")
    public List<Model> printAvailableModelsByBrandId(@PathVariable("id") int id) {
        return vehicleService.printAvailableModelsByBrandId(id);
    }
}
