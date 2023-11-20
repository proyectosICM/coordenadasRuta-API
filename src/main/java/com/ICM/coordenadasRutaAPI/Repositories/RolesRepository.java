package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {
}
