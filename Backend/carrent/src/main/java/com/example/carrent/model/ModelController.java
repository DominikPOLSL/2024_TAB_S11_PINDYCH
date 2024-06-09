package com.example.carrent.model;

import com.example.carrent.BrandModelRequest;
import com.example.carrent.brand.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    private final ModelService modelService;
    private final BrandService brandService;

    @Autowired
    public ModelController(ModelService modelService, BrandService brandService) {
        this.modelService = modelService;
        this.brandService = brandService;
    }

    /**
     * Get all models.
     *
     * @return list of models
     */
    @GetMapping
    public List<Model> getModels() {
        return modelService.getModels();
    }

    /**
     * Add a new model.
     *
     * @param model model to add
     * @return response entity with the created model or error message
     */
    @PostMapping
    public ResponseEntity<?> addNewModel(@RequestBody Model model) {
        int brandId = model.getBrandId();
        if (!brandService.existById(brandId)) {
            return ResponseEntity.badRequest().body("Brand does not exist.");
        }
        Model createdModel = modelService.addNewModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    /**
     * Get model by ID.
     *
     * @param id model ID
     * @return response entity with the model or not found status
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable("id") int id) {
        Model model = modelService.findById(id);
        if (model == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(model);
    }

    /**
     * Delete model by ID.
     *
     * @param id model ID
     * @return response entity with status OK or not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") int id) {
        if (!modelService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        modelService.deleteModelById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Get models by brand ID.
     *
     * @param id brand ID
     * @return list of models for the brand
     */
    @GetMapping("/getModelsByBrandId/{id}")
    public List<Model> getModelsByBrandId(@PathVariable("id") int id) {
        return modelService.getModelsByBrandId(id);
    }

    /**
     * Create a brand and model.
     *
     * @param brandModelRequest request containing brand and model names
     */
    @PostMapping("/createBrandModel")
    public ResponseEntity<?> createBrandModel(@RequestBody BrandModelRequest brandModelRequest) {
        modelService.addBrandModel(brandModelRequest.getBrandName(), brandModelRequest.getModelName());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
