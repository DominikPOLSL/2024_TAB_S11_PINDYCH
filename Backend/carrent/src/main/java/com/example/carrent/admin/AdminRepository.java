package com.example.carrent.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing {@link Admin} entities.
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    /**
     * Finds an admin by their login.
     * 
     * @param login the login of the admin
     * @return an optional containing the found admin, or empty if no admin was
     *         found
     */
    Optional<Admin> findByLogin(String login);
}
