package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for managing countries (PaisesModel) in the application.
 */
@Repository
public interface PaisesRepository extends JpaRepository<PaisesModel, Long> {
}
