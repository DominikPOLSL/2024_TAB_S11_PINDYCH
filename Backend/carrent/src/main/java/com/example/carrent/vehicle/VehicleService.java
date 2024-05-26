package com.example.carrent.vehicle;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    public boolean existById(int id) {
        return vehicleRepository.existsById(id);
    }

    public Optional<Vehicle> deleteVehicleById(int id) {
        Optional<Vehicle> v = vehicleRepository.findById(id);
        vehicleRepository.deleteById(id);
        return v;
    }

    public Vehicle findById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle save(Vehicle existingVehicle) {
        return vehicleRepository.save(existingVehicle);
    }
    public VehiclePrint PrintVehicle(int id)
    {
        Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
        Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
        Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

        return new VehiclePrint(
                vehicle.getVehicleId(),
                model.getModelName() ,
                brand.getBrandName(),
                vehicle.getFuel(),
                vehicle.getTotalDistance(),
                vehicle.getYearOfProduction(),
                vehicle.getPower(),
                "Kuba",
                "Małysz");
    }


    public List<VehiclePrint> printAllVehicles() {
        ArrayList<VehiclePrint> list = new ArrayList<VehiclePrint>();

        for(Vehicle vehicle : vehicleRepository.findAll())
        {
            Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
            Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);
            list.add(new VehiclePrint(
                    vehicle.getVehicleId(),
                    model.getModelName() ,
                    brand.getBrandName(),
                    vehicle.getFuel(),
                    vehicle.getTotalDistance(),
                    vehicle.getYearOfProduction(),
                    vehicle.getPower(),
                    "Kuba",
                    "Małysz"));
        }
        return list;
    }

    public List<VehicleBrandModel> printAvailableCars() {
        ArrayList<VehicleBrandModel> list = new ArrayList<VehicleBrandModel>();
        for(Vehicle vehicle : vehicleRepository.findAll())
        {
            Model model = modelRepository.findById(vehicle.getModelId()).orElse(null);
            Brand brand = brandRepository.findById(model.getBrandId()).orElse(null);

            list.add(new VehicleBrandModel(
                    model.getModelName(),
                    brand.getBrandName()
            ));

        }
        return list;

    }
}
