package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.CoordenadasModel;
import com.ICM.coordenadasRutaAPI.Models.EmpresasModel;
import com.ICM.coordenadasRutaAPI.Models.RutasModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutasRepository extends JpaRepository<RutasModel, Long> {
    List<RutasModel> findByEmpresasModel(EmpresasModel empresasModel);

    List<RutasModel> findByEmpresasModelIdAndEstado(Long idempresa, Boolean estado);
}
