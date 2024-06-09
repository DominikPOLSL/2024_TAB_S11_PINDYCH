package com.example.carrent.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing brand-related operations.
 */
@Service
public class BrandService {

    @Autowired
    private BrandRepository brandRepository;

    /**
     * Retrieves a list of all brands.
     * 
     * @return a list of all brands
     */
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    }

    /**
     * Adds a new brand.
     * 
     * @param brand the brand to add
     * @return the added brand
     */
    public Brand addNewBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    /**
     * Checks if a brand exists by its ID.
     * 
     * @param id the ID to check
     * @return true if a brand exists with the given ID, false otherwise
     */
    public boolean existById(int id) {
        return brandRepository.existsById(id);
    }

    /**
     * Deletes a brand by its ID.
     * 
     * @param id the ID of the brand to delete
     */
    public void deleteBrandById(int id) {
        brandRepository.deleteById(id);
    }

    /**
     * Finds a brand by its ID.
     * 
     * @param id the ID of the brand to find
     * @return the found brand, or null if no brand was found
     */
    public Brand findById(int id) {
        return brandRepository.findById(id).orElse(null);
    }
}
