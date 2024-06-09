/**
 * @file VehicleCarGiverRepository.java
 * @brief This file contains the repository interface for VehicleCarGiver entities.
 */

package com.example.carrent.vehiclecargiver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCarGiverRepository extends JpaRepository<VehicleCarGiver, Integer> {
}
