package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    List<RutasModel> findByEmpresasModel(EmpresasModel empresasModel);
}
