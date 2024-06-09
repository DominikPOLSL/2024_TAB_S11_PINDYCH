package com.example.carrent.managegiver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing ManageGiver entities.
 */
@Repository
public interface ManageGiverRepository extends JpaRepository<ManageGiver, Integer> {
}
