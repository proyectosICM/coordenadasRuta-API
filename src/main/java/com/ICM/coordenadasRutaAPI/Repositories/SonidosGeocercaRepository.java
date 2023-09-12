package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.SonidosGeocercaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SonidosGeocercaRepository extends JpaRepository<SonidosGeocercaModel, Long> {
}
