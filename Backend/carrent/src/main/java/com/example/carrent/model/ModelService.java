package com.example.carrent.model;

import com.example.carrent.brand.Brand;
import com.example.carrent.brand.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ModelService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    /**
     * Constructor for ModelService.
     * @param brandRepository The repository for brands.
     * @param modelRepository The repository for models.
     */
    @Autowired
    public ModelService(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }
    /**
     * Retrieves all models.
     * @return A list of all models.
     */
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

    public List<Model> getModelsByBrandId(int id) {
        return modelRepository.findByBrandId(id);
    }

    public int addBrandModel(String brandName, String modelName) {
        Brand brand = brandRepository.findByName(brandName).orElseGet(() -> brandRepository.save(new Brand(brandName)));
        modelRepository.save(new Model(modelName, brand.getBrandId()));
        return 1;
    }
}
