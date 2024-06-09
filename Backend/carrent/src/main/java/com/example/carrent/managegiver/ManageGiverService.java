package com.example.carrent.managegiver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing ManageGiver entities.
 */
@Service
public class ManageGiverService {

    private final ManageGiverRepository manageGiverRepository;

    /**
     * Constructor for ManageGiverService.
     * 
     * @param manageGiverRepository The repository for ManageGiver entities.
     */
    @Autowired
    public ManageGiverService(ManageGiverRepository manageGiverRepository) {
        this.manageGiverRepository = manageGiverRepository;
    }

    /**
     * Retrieve all ManageGiver entities.
     * 
     * @return A list of all ManageGiver entities.
     */
    public List<ManageGiver> getAllManageGivers() {
        return manageGiverRepository.findAll();
    }

    /**
     * Add a new ManageGiver entity.
     * 
     * @param manageGiver The ManageGiver entity to add.
     */
    public void addManageGiver(ManageGiver manageGiver) {
        manageGiverRepository.save(manageGiver);
    }

    /**
     * Retrieve a ManageGiver entity by its ID.
     * 
     * @param id The ID of the ManageGiver entity to retrieve.
     * @return An optional containing the ManageGiver entity, or empty if not found.
     */
    public Optional<ManageGiver> getManageGiverById(int id) {
        return manageGiverRepository.findById(id);
    }

    /**
     * Delete a ManageGiver entity by its ID.
     * 
     * @param id The ID of the ManageGiver entity to delete.
     */
    public void deleteManageGiver(int id) {
        manageGiverRepository.deleteById(id);
    }

    /**
     * Update a ManageGiver entity.
     * 
     * @param manageGiver The ManageGiver entity to update.
     */
    public void updateManageGiver(ManageGiver manageGiver) {
        manageGiverRepository.save(manageGiver);
    }
}
