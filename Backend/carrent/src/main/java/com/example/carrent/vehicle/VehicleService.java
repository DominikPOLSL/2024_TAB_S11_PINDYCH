package com.example.carrent.vehicle;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing Vehicle entities.
 */
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    /**
     * Constructs a new VehicleService.
     *
     * @param vehicleRepository the vehicle repository
     * @param brandRepository the brand repository
     * @param modelRepository the model repository
     */
    public VehicleService(VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    /**
     * Retrieves a list of all vehicles.
     *
     * @return a list of vehicles
     */
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    /**
     * Adds a new vehicle.
     *
     * @param vehicle the vehicle to add
     * @return the added vehicle
     */
    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    /**
     * Checks if a vehicle exists by its id.
     *
     * @param id the vehicle id
     * @return true if the vehicle exists, false otherwise
     */
    public boolean existsById(int id) {
        return vehicleRepository.existsById(id);
    }

    /**
     * Deletes a vehicle by its id.
     *
     * @param id the vehicle id
     * @return an optional containing the deleted vehicle if it existed, empty otherwise
     */
    public Optional<Vehicle> deleteVehicleById(int id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            vehicleRepository.deleteById(id);
        }
        return vehicleOptional;
    }

    /**
     * Finds a vehicle by its id.
     *
     * @param id the vehicle id
     * @return an optional containing the vehicle if found, empty otherwise
     */
    public Optional<Vehicle> findById(int id) {
        return vehicleRepository.findById(id);
    }

    /**
     * Saves a vehicle entity.
     *
     * @param existingVehicle the vehicle to save
     * @return the saved vehicle
     */
    public Vehicle save(Vehicle existingVehicle) {
        return vehicleRepository.save(existingVehicle);
    }

    /**
     * Prints the details of a vehicle by its id.
     *
     * @param id the vehicle id
     * @return a printable vehicle record or null if not found
     */
    public VehiclePrint printVehicle(int id) {
        return vehicleRepository.findById(id)
                .map(vehicle -> {
                    Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                    if (model == null) return null;
                    Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
                    if (brand == null) return null;
                    return new VehiclePrint(
                            vehicle.getVehicleId(),
                            model.getModelName(),
                            brand.getBrandName(),
                            vehicle.getFuel(),
                            vehicle.getTotalDistance(),
                            vehicle.getYearOfProduction(),
                            vehicle.getPower(),
                            "Kuba",
                            "Małysz"
                    );
                }).orElse(null);
    }

    /**
     * Prints the details of all vehicles.
     *
     * @return a list of printable vehicle records
     */
    public List<VehiclePrint> printAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> {
                    Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
                    if (model == null) return null;
                    Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
                    if (brand == null) return null;
                    return new VehiclePrint(
                            vehicle.getVehicleId(),
                            model.getModelName(),
                            brand.getBrandName(),
                            vehicle.getFuel(),
                            vehicle.getTotalDistance(),
                            vehicle.getYearOfProduction(),
                            vehicle.getPower(),
                            "Kuba",
                            "Małysz"
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Prints the list of available brands.
     *
     * @return a list of brands
     */
    public List<Brand> printAvailableBrands() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null)
                .map(model -> brandRepository.findById(model.getBrandId()).orElse(null))
                .filter(brand -> brand != null)
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Prints the list of available models.
     *
     * @return a list of models
     */
    public List<Model> printAvailableModels() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null)
                .distinct()
                .collect(Collectors.toList());

    }

    /**
     * Prints the list of available models for a given brand id.
     *
     * @param id the brand id
     * @return a list of models for the given brand id
     */
    public List<Model> printAvailableModelsByBrandId(int id) {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null && model.getBrandId() == id)
                .distinct()
                .collect(Collectors.toList());
    }
}
