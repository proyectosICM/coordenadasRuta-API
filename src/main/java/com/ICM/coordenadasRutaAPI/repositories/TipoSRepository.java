package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.TipoSModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing sound types (TipoSModel) in the application.
 */
@Repository
public interface TipoSRepository extends JpaRepository<TipoSModel, Long> {
}
