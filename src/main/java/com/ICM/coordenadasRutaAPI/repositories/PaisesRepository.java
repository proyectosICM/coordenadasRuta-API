package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.PaisesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing countries (PaisesModel) in the application.
 */
@Repository
public interface PaisesRepository extends JpaRepository<PaisesModel, Long> {
}
