package com.example.carrent.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carrent.brand.BrandService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "api/model")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Model> getModels() {
        return modelService.getModels();

    }

    @PostMapping()
    public ResponseEntity<?> addNewModel(@RequestBody Model model) {
        int brandId = model.getBrandId();
        if (!brandService.existById(model.getBrandId())) {
            return ResponseEntity.badRequest().body("Brand does not exist.");
        }
        Model createdModel = modelService.addNewModel(model);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdModel);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable("id") int id) {
        if (!modelService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        modelService.deleteModelById(id);
        return ResponseEntity.ok().build();
    }

}
