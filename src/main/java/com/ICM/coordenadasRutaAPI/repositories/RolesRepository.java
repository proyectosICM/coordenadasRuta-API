package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing roles (RolesModel) in the application.
 */
@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
}