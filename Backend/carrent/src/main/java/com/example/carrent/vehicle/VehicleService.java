package com.example.carrent.vehicle;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.model.Model;
import com.example.carrent.model.ModelRepository;
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
    public String printVehicle(int id)
    {
        Vehicle vehicle = vehicleRepository.getReferenceById(id);
        Model model = modelRepository.getReferenceById(vehicle.getModelId());
        Brand brand = brandRepository.getReferenceById(model.getBrandId());

        return "{" +
                " vehicleId='" + id + "'" +
                ", brand='" + brand.getBrandName() + "'" +
                ", model='" + model.getModelName() + "'" +
                ", fuel='" + "fuel" + "'" +
                ", distance='" + vehicle.getTotalDistance() + "'" +
                ", yearOfProduction='" + 2005 + "'" +
                ", power='" + 100 + "'" +
                ", guardian='" + "Jakub Małysz" + "'" +
                "}";
    }
}
