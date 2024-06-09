package com.example.carrent.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Rest controller for managing brand-related operations.
 */
@RestController
@RequestMapping(path = "api/brand")
public class BrandController {

    private final BrandService brandService;

    /**
     * Constructs a BrandController with the specified brand service.
     * 
     * @param brandService the brand service
     */
    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * Retrieves a list of all brands.
     * 
     * @return a list of all brands
     */
    @GetMapping
    public List<Brand> getBrands() {
        return brandService.getBrands();
    }

    /**
     * Adds a new brand.
     * 
     * @param brand the brand to add
     * @return the response entity with the created brand
     */
    @PostMapping()
    public ResponseEntity<?> addNewBrand(@RequestBody Brand brand) {
        Brand createdBrand = brandService.addNewBrand(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBrand);
    }

    /**
     * Retrieves a brand by its ID.
     * 
     * @param id the ID of the brand to retrieve
     * @return the response entity with the found brand or a 404 status if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBrandById(@PathVariable("id") int id) {
        Brand brand = brandService.findById(id);
        if (brand == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }

    /**
     * Deletes a brand by its ID.
     * 
     * @param id the ID of the brand to delete
     * @return the response entity indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable("id") int id) {
        if (!brandService.existById(id)) {
            return ResponseEntity.notFound().build();
        }
        brandService.deleteBrandById(id);
        return ResponseEntity.ok().build();
    }
}
