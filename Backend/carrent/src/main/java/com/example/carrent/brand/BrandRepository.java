package com.example.carrent.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Brand} entities.
 */
@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    /**
     * Finds a brand by its name.
     * 
     * @param brandName the name of the brand
     * @return the brand with the specified name
     */
    Brand findByBrandName(String brandName);

}
