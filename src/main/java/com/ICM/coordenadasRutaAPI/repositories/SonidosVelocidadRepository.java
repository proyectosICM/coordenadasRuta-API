package com.ICM.coordenadasRutaAPI.repositories;

import com.ICM.coordenadasRutaAPI.models.SonidosVelocidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing speed sounds (SonidosVelocidadModel) in the application.
 */
@Repository
public interface SonidosVelocidadRepository extends JpaRepository<SonidosVelocidadModel, Long> {
}
