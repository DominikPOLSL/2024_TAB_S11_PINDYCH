package com.example.carrent.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Reservation entities.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
