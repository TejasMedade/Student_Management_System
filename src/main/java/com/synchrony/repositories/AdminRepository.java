package com.synchrony.repositories;

import com.synchrony.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for interacting with the Admin entity.
 * Extends JpaRepository to provide CRUD operations for Admin entities.
 *
 * @author Tejas_Medade
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    // No additional methods are required for this basic repository as JpaRepository provides CRUD operations by default.
}
