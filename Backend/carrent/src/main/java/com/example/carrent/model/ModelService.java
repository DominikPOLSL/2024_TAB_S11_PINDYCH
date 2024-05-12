package com.example.carrent.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import com.example.carrent.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private static BrandRepository brandRepository;
    private static ModelRepository modelRepository;


    public ModelService(BrandRepository brandRepository, ModelRepository modelRepository) {
        ModelService.brandRepository = brandRepository;
        ModelService.modelRepository = modelRepository;
    }

    public List<Model> getModels() {
        return modelRepository.findAll();
    }

    public Model addNewModel(Model model) {
        return modelRepository.save(model);
    }

    public boolean existById(int id) {
        return modelRepository.existsById(id);
    }

    public void deleteModelById(int id) {
        modelRepository.deleteById(id);
    }

    public Model findById(int id) {
        return modelRepository.findById(id).orElse(null);
    }

    public static List<Model> getModelsByBrandId(int id) {
        List<Model> list = new ArrayList<Model>();
        for(Model m : modelRepository.findAll())
        {
            if(m.getBrandId()==id)
            {
                list.add(m);
            }
        }
        return list;
    }

    public int addBrandModel( String brandName, String modelName) {

        for(Brand b : brandRepository.findAll())
        {
            if(Objects.equals(b.getBrandName(), brandName))
            {
                modelRepository.save(new Model(modelName, b.getBrandId()));
                return 0;
            }
        }
        Brand brand = brandRepository.save(new Brand(brandName));
        modelRepository.save(new Model(modelName, brand.getBrandId()));
        return 1;
    }
}
