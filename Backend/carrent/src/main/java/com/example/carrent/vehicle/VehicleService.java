package com.example.carrent.vehicle;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.util.List;



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

    public void deleteVehicleById(int id) {
        vehicleRepository.deleteById(id);
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
                "fuel",
                200,
                2005,
                120,
                "Kuba",
                "Małysz");
    }
}
