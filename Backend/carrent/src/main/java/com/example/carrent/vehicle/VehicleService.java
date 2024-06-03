package com.example.carrent.vehicle;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public VehicleService(VehicleRepository vehicleRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle addNewVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public boolean existsById(int id) {
        return vehicleRepository.existsById(id);
    }

    public Optional<Vehicle> deleteVehicleById(int id) {
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);
        if (vehicleOptional.isPresent()) {
            vehicleRepository.deleteById(id);
        }
        return vehicleOptional;
    }

    public Optional<Vehicle> findById(int id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle save(Vehicle existingVehicle) {
        return vehicleRepository.save(existingVehicle);
    }

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
    public List<Brand> printAvailableBrands() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null)
                .map(model -> brandRepository.findById(model.getBrandId()).orElse(null))
                .filter(brand -> brand != null)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Model> printAvailableModels() {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null)
                .distinct()
                .collect(Collectors.toList());

    }

    public List<Model> printAvailableModelsByBrandId(int id) {
        return vehicleRepository.findAll().stream()
                .map(vehicle -> modelRepository.findById(vehicle.getModelId()).orElse(null))
                .filter(model -> model != null && model.getBrandId() == id)
                .distinct()
                .collect(Collectors.toList());
    }
}
