package com.example.carrent.managegiver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManageGiverRepository extends JpaRepository<ManageGiver, Integer> {
}
