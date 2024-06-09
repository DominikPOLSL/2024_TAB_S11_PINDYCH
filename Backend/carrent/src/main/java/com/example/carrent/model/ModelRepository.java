package com.example.carrent.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * Repository for accessing model data.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    /**
     * Finds models by brand ID.
     * @param brandId The ID of the brand.
     * @return A list of models belonging to the specified brand.
     */
    List<Model> findByBrandId(int brandId);
    /**
     * Finds a model by name.
     * @param modelName The name of the model to find.
     * @return An optional containing the found model, or empty if not found.
     */
    Optional<Model> findByName(String modelName);
}
