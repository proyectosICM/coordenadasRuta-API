package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.TipoSModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing sound types (TipoSModel) in the application.
 */
@Repository
public interface TipoSRepository extends JpaRepository<TipoSModel, Long> {
}
