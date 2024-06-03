package com.example.carrent.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByAdminLogin(String login);
}