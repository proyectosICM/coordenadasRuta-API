package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.SonidosVelocidadModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SonidosVelocidadRepository extends JpaRepository<SonidosVelocidadModel, Long> {
}
