package com.example.carrent.model;

import java.util.List;

import com.example.carrent.BrandModelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.carrent.brand.BrandService;

@RestController
@RequestMapping(path = "api/model")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Model> getModels() {
        return modelService.getModels();

    }

    @PostMapping()
    public ResponseEntity<?> addNewModel(@RequestBody Model model) {
        int brandId = model.getBrandId();
        if (!brandService.existById(brandId)) {
            return ResponseEntity.badRequest().body("Brand does not exist.");
        }
        Model createdModel = modelService.addNewModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModelById(@PathVariable("id") int id) {
        Model model = modelService.findById(id);
        if (model == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") int id) {
        if (!modelService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        modelService.deleteModelById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getModelsByBrandId/{id}")
    public List<Model> getModelsByBrandId(@PathVariable("id") int id) {
        return ModelService.getModelsByBrandId(id);
    }

    @PostMapping("/createBrandModel")
    public void createBrandModel(@RequestBody BrandModelRequest brandModelRequest) {
        modelService.addBrandModel(brandModelRequest.getBrandName(), brandModelRequest.getModelName());
    }
}
