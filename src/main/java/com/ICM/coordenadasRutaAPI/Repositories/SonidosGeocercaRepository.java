package com.ICM.coordenadasRutaAPI.Repositories;

import com.ICM.coordenadasRutaAPI.Models.PaisesModel;
import com.ICM.coordenadasRutaAPI.Models.SonidosGeocercaModel;
import com.ICM.coordenadasRutaAPI.Models.TipoSModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repository interface for managing geofence sounds (SonidosGeocercaModel) in the application.
 */
@Repository
public interface SonidosGeocercaRepository extends JpaRepository<SonidosGeocercaModel, Long> {
    /**
     * Find geofence sounds by associated country.
     *
     * @param paisesModel The country model to search geofence sounds for.
     * @return A list of geofence sounds associated with the given country.
     */
    List<SonidosGeocercaModel> findByPaisesModel(PaisesModel paisesModel);

    /**
     * Find geofence sounds by associated sound type.
     *
     * @param tipoSModel The sound type model to search geofence sounds for.
     * @return A list of geofence sounds associated with the given sound type.
     */
    List<SonidosGeocercaModel> findByTipoSModel(TipoSModel tipoSModel);

    /**
     * Find geofence sounds by associated country and sound type.
     *
     * @param paisesModel The country model to search geofence sounds for.
     * @param tipoSModel  The sound type model to search geofence sounds for.
     * @return A list of geofence sounds associated with the given country and sound type.
     */
    List<SonidosGeocercaModel> findByPaisesModelAndTipoSModel(PaisesModel paisesModel, TipoSModel tipoSModel);
}
