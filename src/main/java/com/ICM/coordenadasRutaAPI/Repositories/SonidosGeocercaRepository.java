package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.Models.TipoSModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SonidosGeocercaRepository extends JpaRepository<SonidosGeocercaModel, Long> {
    List<SonidosGeocercaModel> findByPaisesModel(PaisesModel paisesModel);
    List<SonidosGeocercaModel> findByTipoSModel(TipoSModel tipoSModel);

    List<SonidosGeocercaModel> findByPaisesModelAndTipoSModel(PaisesModel paisesModel, TipoSModel tipoSModel);
}
