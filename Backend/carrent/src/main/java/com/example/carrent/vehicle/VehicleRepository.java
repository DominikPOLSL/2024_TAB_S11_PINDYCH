package com.example.carrent.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Vehicle entities.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

}
