package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.SonidosVelocidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing speed sounds (SonidosVelocidadModel) in the application.
 */
@Repository
public interface SonidosVelocidadRepository extends JpaRepository<SonidosVelocidadModel, Long> {
}
