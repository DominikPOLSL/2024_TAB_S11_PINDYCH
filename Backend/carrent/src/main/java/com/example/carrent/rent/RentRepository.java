package com.example.carrent.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
    @Query("SELECT r.reservationId FROM Rent r WHERE r.rentId = :rentId")
    Integer findReservationIdByRentId(int rentId);
}
